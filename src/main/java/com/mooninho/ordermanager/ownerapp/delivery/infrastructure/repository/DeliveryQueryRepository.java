package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;

import java.util.Optional;

public interface DeliveryQueryRepository {

    boolean isExistCompany(Long deliveryCompanyId);

    Optional<DeliveryStatus> getDeliveryStatus(Long deliveryId);

    boolean isDeliveryOwner(Long deliveryId, Long deliveryDriverId);

    Long getDeliveryId(Long orderId);

    boolean isDeliveryComplete(Long deliveryId);
}
