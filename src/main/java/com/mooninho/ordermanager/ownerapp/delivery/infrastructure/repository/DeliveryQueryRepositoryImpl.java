package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mooninho.ordermanager.ownerapp.delivery.domain.entity.QDelivery.delivery;
import static com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.QDeliveryCompany.deliveryCompany;

@Repository
@RequiredArgsConstructor
public class DeliveryQueryRepositoryImpl implements DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExistCompany(Long deliveryCompanyId) {
        return queryFactory
                .selectOne()
                .from(deliveryCompany)
                .where(deliveryCompany.id.eq(deliveryCompanyId))
                .fetchFirst() != null;
    }

    @Override
    public Optional<DeliveryStatus> getDeliveryStatus(Long deliveryId) {
        return Optional.ofNullable(queryFactory
                .select(delivery.deliveryStatus)
                .from(delivery)
                .where(delivery.id.eq(deliveryId))
                .fetchOne());
    }

    @Override
    public boolean isDeliveryOwner(Long deliveryId, Long deliveryDriverId) {
        return queryFactory
                .selectOne()
                .from(delivery)
                .where(
                        delivery.id.eq(deliveryId),
                        delivery.deliveryDriver.id.eq(deliveryDriverId)
                )
                .fetchFirst() != null;
    }

    @Override
    public Long getDeliveryId(Long orderId) {
        return queryFactory
                .select(delivery.id)
                .from(delivery)
                .where(delivery.order.id.eq(orderId))
                .fetchOne();
    }

    @Override
    public boolean isDeliveryComplete(Long deliveryId) {
        return queryFactory
                .selectOne()
                .from(delivery)
                .where(
                        delivery.id.eq(deliveryId),
                        delivery.deliveryStatus.eq(DeliveryStatus.COMPLETE)
                )
                .fetchFirst() != null;
    }
}
