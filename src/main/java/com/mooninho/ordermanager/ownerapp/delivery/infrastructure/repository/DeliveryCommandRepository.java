package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

public interface DeliveryCommandRepository {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    void updateDeliveryToDispatch(Long deliveryId, Long deliveryDriverId);

    void updateDeliveryStatus(Long deliveryId, DeliveryStatus deliveryStatus);
}
