package com.order.manager.service;

import com.order.manager.dto.delivery.DeliveryResponseDto;
import com.order.manager.enums.state.DeliveryState;
import com.order.manager.enums.state.OrderState;
import com.order.manager.exception.exception.delivery.*;
import com.order.manager.repository.DeliveryQueryRepository;
import com.order.manager.repository.OrderQueryRepository;
import com.order.manager.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.order.manager.util.account.LoggedInUserinfoProvider.loggedInUserKeyFetcher;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderQueryRepository orderQueryRepository;
    private final StoreQueryRepository storeQueryRepository;
    private final DeliveryQueryRepository deliveryQueryRepository;

    @Transactional
    public void deliveryDriverAssignment(Long deliveryId, Long deliveryDriverId) {

        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new EmptyDeliveryException();
        }
        if (isNotWaiting(foundDelivery)) {
            throw new NotReadyException();
        }

        Long driverCompanyId = deliveryQueryRepository.findDeliveryCompanyIdByDeliveryDriverId(deliveryDriverId);
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

        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new EmptyDeliveryException();
        }
        if (isDeliveryDriverMismatch(deliveryDriverId, foundDelivery)) {
            throw new DriverMismatchException();
        }
        if (isNotDispatch(foundDelivery)) {
            throw new NotStateInDispatchException();
        }
        deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryState);
    }

    @Transactional
    public void deliveryComplete(Long deliveryId, Long deliveryDriverId, DeliveryState deliveryState) {

        Long storeId = storeQueryRepository.findStoreIdByUserKey(loggedInUserKeyFetcher());

        DeliveryResponseDto foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new EmptyDeliveryException();
        }
        if (isDeliveryDriverMismatch(deliveryDriverId, foundDelivery)) {
            throw new DriverMismatchException();
        }
        if (isNotPickUp(foundDelivery)) {
            throw new NotStateInPickUpException();
        }

        Long orderId = foundDelivery.getOrderId();

        deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryState);
//        orderQueryRepository.updateOrderStateToPreparing(orderId, storeId, OrderState.COMPLETE);
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
