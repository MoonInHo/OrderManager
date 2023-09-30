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

//    public DeliveryResponseDto findDeliveryByDeliveryId(Long deliveryId) {
//        return queryFactory
//                .select(
//                        Projections.constructor(
//                                DeliveryResponseDto.class,
//                                delivery.order.id,
//                                delivery.deliveryState,
//                                delivery.deliveryCompany,
//                                delivery.deliveryDriver.id
//                        )
//                )
//                .from(delivery)
//                .where(delivery.id.eq(deliveryId))
//                .fetchFirst();
//        return null;
//    }

    public Long findDeliveryCompanyIdByDeliveryDriverId(Long deliveryDriverId) {
//        return queryFactory
//                .select(deliveryDriver.deliveryCompany.id)
//                .from(deliveryDriver)
//                .where(deliveryDriver.id.eq(deliveryDriverId))
//                .fetchFirst();
        return null;
    }

    public Long updateDeliveryStateToDispatch(Long deliveryId, Long deliveryDriverId) {
//        return queryFactory
//                .update(delivery)
//                .set(delivery.deliveryDriver.id, deliveryDriverId)
//                .set(delivery.deliveryState, DeliveryState.DISPATCH)
//                .where(delivery.id.eq(deliveryId))
//                .execute();
        return null;
    }

    public Long updateDeliveryStateToPickup(Long deliveryId) {
//        return queryFactory
//                .update(delivery)
//                .set(delivery.deliveryState, DeliveryState.PICKUP)
//                .where(delivery.id.eq(deliveryId))
//                .execute();
        return null;
    }

    public Long updateDeliveryStateToComplete(Long deliveryId) {
//        return queryFactory
//                .update(delivery)
//                .set(delivery.deliveryState, DeliveryState.COMPLETE)
//                .where(delivery.id.eq(deliveryId))
//                .execute();
        return null;
    }
}
