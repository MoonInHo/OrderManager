package com.mooninho.ordermanager.임시.service;

import com.mooninho.ordermanager.임시.dto.delivery.DeliveryResponseDto;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import com.mooninho.ordermanager.임시.repository.DeliveryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryQueryRepository deliveryQueryRepository;

    @Transactional
    public void deliveryDriverAssignment(Long deliveryId, Long deliveryDriverId) {

        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
//        if (foundDelivery == null) {
//            throw new EmptyDeliveryException();
//        }
//        if (isNotWaiting(foundDelivery)) {
//            throw new InvalidDeliveryStateException("배차 대기중이 아닙니다.");
//        }
//
//        Long driverCompanyId = deliveryQueryRepository.findDeliveryCompanyIdByDeliveryDriverId(deliveryDriverId);
//        if (isCompanyMismatch(foundDelivery, driverCompanyId)) {
//            throw new CompanyMismatchException();
//        }
//
//        Long updatedRow = deliveryQueryRepository.updateDeliveryStateToDispatch(deliveryId, deliveryDriverId);
//        if (updatedRow == null) {
//            throw new DriverAssignmentFailureException();
//        }
    }

    @Transactional
    public void deliveryPickUp(Long deliveryId, Long deliveryDriverId) {

        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
//        if (foundDelivery == null) {
//            throw new EmptyDeliveryException();
//        }
//        if (isDeliveryDriverMismatch(deliveryDriverId, foundDelivery)) {
//            throw new DriverMismatchException();
//        }
//        if (isNotDispatch(foundDelivery)) {
//            throw new NotStateInDispatchException();
//        }
//        Long updatedRow = deliveryQueryRepository.updateDeliveryStateToPickup(deliveryId);
//        if (updatedRow == 0) {
//            throw new NotChangedDeliveryStateException();
//        }
    }

    @Transactional
    public void deliveryComplete(Long deliveryId, Long deliveryDriverId) {

        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
//        if (foundDelivery == null) {
//            throw new EmptyDeliveryException();
//        }
//        if (isDeliveryDriverMismatch(deliveryDriverId, foundDelivery)) {
//            throw new DriverMismatchException();
//        }
//        if (isNotPickUp(foundDelivery)) {
//            throw new NotStateInPickUpException();
//        }
//
//        Long updatedRow = deliveryQueryRepository.updateDeliveryStateToComplete(deliveryId);
//        if (updatedRow == 0) {
//            throw new NotChangedDeliveryStateException();
//        }
    }

    private boolean isNotWaiting(DeliveryResponseDto foundDelivery) {
        return !foundDelivery.getDeliveryState().equals(DeliveryState.WAITING);
    }

    private boolean isCompanyMismatch(DeliveryResponseDto foundDelivery, Long driverCompanyId) {
        return !driverCompanyId.equals(foundDelivery.getDeliveryCompany().getId());
    }

    private boolean isNotPickUp(DeliveryResponseDto foundDelivery) {
        return !foundDelivery.getDeliveryState().equals(DeliveryState.PICKUP);
    }

    private boolean isNotDispatch(DeliveryResponseDto foundDelivery) {
        return !foundDelivery.getDeliveryState().equals(DeliveryState.DISPATCH);
    }

    private boolean isDeliveryDriverMismatch(Long deliveryDriverId, DeliveryResponseDto foundDelivery) {
        return !foundDelivery.getDeliveryDriverId().equals(deliveryDriverId);
    }
}
