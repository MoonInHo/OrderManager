package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;

public interface DeliveryCommandRepository {

    void updateDeliveryToDispatch(Long deliveryId, Long deliveryDriverId);

    void updateDeliveryStatus(Long deliveryId, DeliveryStatus deliveryStatus);
}
