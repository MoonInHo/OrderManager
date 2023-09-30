package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository;

public interface DeliveryCommandRepository {

    void updateDeliveryStatusToDispatch(Long deliveryId, Long deliveryDriverId);
}
