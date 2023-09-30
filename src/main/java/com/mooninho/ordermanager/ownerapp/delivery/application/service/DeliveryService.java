package com.mooninho.ordermanager.ownerapp.delivery.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import com.mooninho.ordermanager.ownerapp.deliverydriver.domain.entity.DeliveryDriver;
import com.mooninho.ordermanager.ownerapp.deliverydriver.domain.repository.DeliveryDriverRepository;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.NotFoundDeliveryException;
import com.mooninho.ordermanager.ownerapp.exception.exception.deliverydriver.NotFoundDeliveryDriverException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void deliveryDriverAssignment(Long deliveryId, Long deliveryDriverId) { // TODO 동시성 이슈 체크 (서로 다른 기사가 요청을 동시에 수락할경우)

        validDeliveryStatus(getDeliveryStatus(deliveryId));

        deliveryRepository.updateDeliveryStatusToDispatch(deliveryId, deliveryDriverId);
    }

    @Transactional(readOnly = true)
    protected DeliveryStatus getDeliveryStatus(Long deliveryId) {
        return deliveryRepository.getDeliveryStatus(deliveryId)
                .orElseThrow(NotFoundDeliveryException::new);
    }

    private void validDeliveryStatus(DeliveryStatus deliveryStatus) {
        if (isNotDeliveryStatusWaiting(deliveryStatus)) {
            throw new InvalidRequestException();
        }
    }

    private boolean isNotDeliveryStatusWaiting(DeliveryStatus deliveryStatus) {
        return !deliveryStatus.equals(DeliveryStatus.WAITING);
    }

//    @Transactional
//    public void deliveryPickUp(Long deliveryId, Long deliveryDriverId) {
//
//        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
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
//    }

//    @Transactional
//    public void deliveryComplete(Long deliveryId, Long deliveryDriverId) {
//
//        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
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
//    }

    //    @Transactional
//    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery(Long storeId) {
//
//        List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList = orderQueryRepository.findDeliveryByDeliveryState(storeId);
//        if (isEmptyInProgressDelivery(fetchedInProgressDeliveryList)) {
//            throw new EmptyDeliveryListException();
//        }
//        return fetchedInProgressDeliveryList;
//    }

//    @Transactional
//    public List<DeliveryDetailResponseDto> deliveryDetailLookup(Long storeId, Long deliveryId) {
//
//        List<DeliveryDetailResponseDto> deliveryInfo = orderQueryRepository.findDeliveryDetail(storeId, deliveryId);
//        if (isEmptyDeliveryDetail(deliveryInfo)) {
//            throw new EmptyDeliveryException();
//        }
//        return deliveryInfo;
//    }

//    private boolean isNotWaiting(DeliveryResponseDto foundDelivery) {
//        return !foundDelivery.getDeliveryState().equals(DeliveryState.WAITING);
//    }
//
//    private boolean isCompanyMismatch(DeliveryResponseDto foundDelivery, Long driverCompanyId) {
//        return !driverCompanyId.equals(foundDelivery.getDeliveryCompany().getId());
//    }
//
//    private boolean isNotPickUp(DeliveryResponseDto foundDelivery) {
//        return !foundDelivery.getDeliveryState().equals(DeliveryState.PICKUP);
//    }
//
//    private boolean isNotDispatch(DeliveryResponseDto foundDelivery) {
//        return !foundDelivery.getDeliveryState().equals(DeliveryState.DISPATCH);
//    }
//
//    private boolean isDeliveryDriverMismatch(Long deliveryDriverId, DeliveryResponseDto foundDelivery) {
//        return !foundDelivery.getDeliveryDriverId().equals(deliveryDriverId);
//    }
}
