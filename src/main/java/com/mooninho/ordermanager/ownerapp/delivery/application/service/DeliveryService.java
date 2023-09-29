package com.mooninho.ordermanager.ownerapp.delivery.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;



    // 배달원 정보 조회

    // 배달업체 키 값 조회

    // 배달원 키 값 조회






    // 배달원용 앱 분리

//    @Transactional
//    public void deliveryDriverAssignment(Long deliveryId, Long deliveryDriverId) {
//
//        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
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
//    }

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
