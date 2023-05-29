package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.dto.delivery.DeliveryRequestDto;
import com.foodtech.mate.enums.state.DeliveryState;
import com.foodtech.mate.enums.state.OrderState;
import com.foodtech.mate.exception.exception.delivery.*;
import com.foodtech.mate.repository.DeliveryQueryRepository;
import com.foodtech.mate.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderQueryRepository orderQueryRepository;
    private final DeliveryQueryRepository deliveryQueryRepository;

    @Transactional
    public void deliveryDriverAssignment(Long deliveryId, DeliveryRequestDto deliveryRequestDto) {

        Long deliveryDriverId = deliveryRequestDto.getDeliveryDriverId();

        Delivery foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (isNotWaiting(foundDelivery)) {
            throw new NotWaitingException();
        }

        Long driverCompanyId = deliveryQueryRepository.findDeliveryDriverCompanyIdByDeliveryDriverId(deliveryDriverId);
        if (isCompanyMismatch(foundDelivery, driverCompanyId)) {
            throw new CompanyMismatchException();
        }

        Long updatedLow = deliveryQueryRepository.updateDeliveryStateAndDriverId(deliveryId, deliveryDriverId);
        if (updatedLow == null) {
            throw new DriverAssignmentFailureException();
        }
    }
    
    @Transactional
    public void deliveryPickUp(Long deliveryId, Long deliveryDriverId, DeliveryState deliveryState) {

        Delivery foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new EmptyDeliveryException();
        }
        if (isDeliveryDriverMismatch(deliveryDriverId, foundDelivery)) {
            throw new DriverMismatchException();
        }
        if (isNotStateInDispatch(foundDelivery)) {
            throw new NotStateInDispatchException();
        }
        deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryState);
    }

    @Transactional
    public void deliveryComplete(Long deliveryId, Long deliveryDriverId, DeliveryState deliveryState) {

        Delivery foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new EmptyDeliveryException();
        }
        if (isDeliveryDriverMismatch(deliveryDriverId, foundDelivery)) {
            throw new DriverMismatchException();
        }
        if (isNotStateInPickUp(foundDelivery)) {
            throw new NotStateInPickUpException();
        }

        Long orderId = foundDelivery.getOrder().getId();

        deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryState);
        orderQueryRepository.updateOrderState(orderId, OrderState.COMPLETE);
    }

    private boolean isNotWaiting(Delivery foundDelivery) {
        return !foundDelivery.getDeliveryState().equals(DeliveryState.WAITING);
    }

    private boolean isCompanyMismatch(Delivery foundDelivery, Long driverCompanyId) {
        return !driverCompanyId.equals(foundDelivery.getDeliveryCompany().getId());
    }

    private boolean isNotStateInPickUp(Delivery foundDelivery) {
        return !foundDelivery.getDeliveryState().equals(DeliveryState.PICKUP);
    }

    private boolean isNotStateInDispatch(Delivery foundDelivery) {
        return !foundDelivery.getDeliveryState().equals(DeliveryState.DISPATCH);
    }

    private boolean isDeliveryDriverMismatch(Long deliveryDriverId, Delivery foundDelivery) {
        return !foundDelivery.getDeliveryDriver().getId().equals(deliveryDriverId);
    }
}
