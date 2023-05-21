package com.foodtech.mate.repository;

import com.foodtech.mate.domain.dto.delivery.InProgressDeliveryDto;
import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.domain.entity.DeliveryCompany;
import com.foodtech.mate.domain.state.DeliveryState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foodtech.mate.domain.entity.QDelivery.delivery;
import static com.foodtech.mate.domain.entity.QDeliveryCompany.deliveryCompany;
import static com.foodtech.mate.domain.entity.QDeliveryDriver.deliveryDriver;
import static com.foodtech.mate.domain.entity.QOrder.order;


@Repository
@RequiredArgsConstructor
public class DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DeliveryCompany findDeliveryCompanyByCompanyName(Company companyName) {
        return queryFactory
                .selectFrom(deliveryCompany)
                .where(deliveryCompany.company.eq(companyName))
                .fetchFirst();
    }

    public Delivery findDeliveryByDeliveryId(Long deliveryId) {
        return queryFactory
                .selectFrom(delivery)
                .where(delivery.id.eq(deliveryId))
                .fetchFirst();
    }

    public Long findDeliveryDriverCompanyIdByDeliveryDriverId(Long deliveryDriverId) {
        return queryFactory
                .select(deliveryDriver.deliveryCompany.id)
                .from(deliveryDriver)
                .where(deliveryDriver.id.eq(deliveryDriverId))
                .fetchFirst();
    }

    public Long updateDeliveryStateAndDriverId(Long deliveryId, Long deliveryDriverId) {
        return queryFactory
                .update(delivery)
                .set(delivery.deliveryDriver.id, deliveryDriverId)
                .set(delivery.deliveryState, DeliveryState.DISPATCH)
                .where(delivery.id.eq(deliveryId))
                .execute();
    }

    public Long updateDeliveryState(Long deliveryId, DeliveryState deliveryState) {
        return queryFactory
                .update(delivery)
                .set(delivery.deliveryState, deliveryState)
                .where(delivery.id.eq(deliveryId))
                .execute();
    }

    public List<InProgressDeliveryDto> findInProgressingDelivery() {
        return queryFactory
                .select(
                        Projections.constructor(
                                InProgressDeliveryDto.class,
                                delivery.id,
                                order.orderTimestamp.orderTimestamp,
                                order.orderType,
                                delivery.deliveryCompany.company,
                                order.paymentType,
                                order.totalPrice,
                                delivery.deliveryState
                        )
                )
                .from(order)
                .join(order.delivery, delivery)
                .where(order.orderType.eq(OrderType.DELIVERY), delivery.deliveryState.ne(DeliveryState.COMPLETE))
                .fetch();
    }
}
