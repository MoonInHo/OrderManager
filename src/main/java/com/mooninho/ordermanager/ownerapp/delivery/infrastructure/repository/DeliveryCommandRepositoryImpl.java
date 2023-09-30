package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mooninho.ordermanager.ownerapp.delivery.domain.entity.QDelivery.delivery;

@Repository
@RequiredArgsConstructor
public class DeliveryCommandRepositoryImpl implements DeliveryCommandRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void updateDeliveryStatusToDispatch(Long deliveryId, Long deliveryDriverId) {
        queryFactory
                .update(delivery)
                .set(delivery.deliveryDriver.id, deliveryDriverId)
                .set(delivery.deliveryStatus, DeliveryStatus.DISPATCH)
                .where(delivery.id.eq(deliveryId))
                .execute();
    }
}
