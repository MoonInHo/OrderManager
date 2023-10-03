package com.mooninho.ordermanager.ownerapp.delivery.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.NotFoundDeliveryException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void deliveryDriverAssignment(Long deliveryId, Long deliveryDriverId) {

        checkDeliveryWaiting(getDeliveryStatus(deliveryId));

        deliveryRepository.updateDeliveryToDispatch(deliveryId, deliveryDriverId);
    }

    @Transactional
    public void deliveryPickUp(Long deliveryId, Long deliveryDriverId) {

        checkDeliveryDispatch(getDeliveryStatus(deliveryId));
        checkDeliveryOwner(deliveryId, deliveryDriverId);

        deliveryRepository.updateDeliveryStatus(deliveryId, DeliveryStatus.PICKUP);
    }

    @Transactional
    public void deliveryComplete(Long deliveryId, Long deliveryDriverId) {

        checkDeliveryPickUp(getDeliveryStatus(deliveryId));
        checkDeliveryOwner(deliveryId, deliveryDriverId);

        deliveryRepository.updateDeliveryStatus(deliveryId, DeliveryStatus.COMPLETE);
    }

    @Transactional(readOnly = true)
    protected DeliveryStatus getDeliveryStatus(Long deliveryId) {
        return deliveryRepository.getDeliveryStatus(deliveryId)
                .orElseThrow(NotFoundDeliveryException::new);
    }

    @Transactional(readOnly = true)
    protected void checkDeliveryOwner(Long deliveryId, Long deliveryDriverId) {

        boolean deliveryOwner = deliveryRepository.isDeliveryOwner(deliveryId, deliveryDriverId);
        if (!deliveryOwner) {
            throw new UnauthorizedException();
        }
    }

    private void checkDeliveryWaiting(DeliveryStatus deliveryStatus) {
        if (!isDeliveryWaiting(deliveryStatus)) {
            throw new InvalidRequestException();
        }
    }

    private void checkDeliveryDispatch(DeliveryStatus deliveryStatus) {
        if (!isDeliveryDispatch(deliveryStatus)) {
            throw new InvalidRequestException();
        }
    }

    private void checkDeliveryPickUp(DeliveryStatus deliveryStatus) {
        if (!isDeliveryPickUp(deliveryStatus)) {
            throw new InvalidRequestException();
        }
    }

    private boolean isDeliveryWaiting(DeliveryStatus deliveryStatus) {
        return deliveryStatus.equals(DeliveryStatus.WAITING);
    }

    private boolean isDeliveryDispatch(DeliveryStatus deliveryStatus) {
        return deliveryStatus.equals(DeliveryStatus.DISPATCH);
    }

    private boolean isDeliveryPickUp(DeliveryStatus deliveryStatus) {
        return deliveryStatus.equals(DeliveryStatus.PICKUP);
    }
}
