package com.foodtech.mate.repository;

import com.foodtech.mate.domain.entity.QDelivery;
import com.foodtech.mate.domain.state.DeliveryState;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QDelivery.delivery;

@Repository
@RequiredArgsConstructor
public class DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DeliveryState findDeliveryByDeliveryId(Long deliveryId) {
        return queryFactory
                .select(delivery.deliveryState)
                .from(delivery)
                .where(delivery.id.eq(deliveryId))
                .fetchFirst();
    }

    public Long updateDeliveryState(Long deliveryId, Long deliveryDriverId) {
        return queryFactory
                .update(delivery)
                .set(delivery.deliveryDriver.id, deliveryDriverId)
                .set(delivery.deliveryState, DeliveryState.DISPATCH)
                .where(delivery.id.eq(deliveryId))
                .execute();
    }
}
