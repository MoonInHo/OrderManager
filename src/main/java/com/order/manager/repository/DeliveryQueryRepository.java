package com.order.manager.repository;

import com.order.manager.dto.delivery.DeliveryResponseDto;
import com.order.manager.enums.state.DeliveryState;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.order.manager.domain.entity.QDelivery.delivery;
import static com.order.manager.domain.entity.QDeliveryDriver.deliveryDriver;


@Repository
@RequiredArgsConstructor
public class DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DeliveryResponseDto findDeliveryByDeliveryId(Long deliveryId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                DeliveryResponseDto.class,
                                delivery.order.id,
                                delivery.deliveryState,
                                delivery.deliveryCompany,
                                delivery.deliveryDriver.id
                        )
                )
                .from(delivery)
                .where(delivery.id.eq(deliveryId))
                .fetchFirst();
    }

    public Long findDeliveryCompanyIdByDeliveryDriverId(Long deliveryDriverId) {
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
}
