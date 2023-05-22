package com.foodtech.mate.repository;

import com.foodtech.mate.domain.dto.delivery.DeliveryInfoDto;
import com.foodtech.mate.domain.dto.delivery.DeliveryTrackingDto;
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

import static com.foodtech.mate.domain.entity.QCustomer.customer;
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

    public List<DeliveryTrackingDto> findInDeliveryByDeliveryState(DeliveryState deliveryState) {
        return queryFactory
                .select(
                        Projections.constructor(
                                DeliveryTrackingDto.class,
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
                .where(order.orderType.eq(OrderType.DELIVERY), delivery.deliveryState.eq(deliveryState))
                .fetch();
    }

    public List<DeliveryInfoDto> findDeliveryInfo(Long deliveryId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                DeliveryInfoDto.class,
                                delivery.deliveryState,
                                deliveryCompany.company,
                                delivery.deliveryTips,
                                deliveryDriver.driverName,
                                deliveryDriver.driverPhone,
                                customer.contact,
                                customer.address,
                                order.orderTimestamp.orderTimestamp,
                                order.orderType
                        )
                )
                .from(delivery)
                .join(delivery.deliveryDriver, deliveryDriver)
                .join(delivery.deliveryCompany, deliveryCompany)
                .join(delivery.order, order)
                .join(order.customer, customer)
                .where(delivery.id.eq(deliveryId))
                .fetch();
    }
}
