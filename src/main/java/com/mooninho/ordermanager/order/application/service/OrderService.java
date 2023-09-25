package com.mooninho.ordermanager.order.application.service;

import com.mooninho.ordermanager.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.exception.exception.order.EmptyOrderListException;
import com.mooninho.ordermanager.exception.exception.owner.OwnerNotFoundException;
import com.mooninho.ordermanager.order.domain.repository.OrderRepository;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;
import com.mooninho.ordermanager.owner.domain.repository.OwnerRepository;
import com.mooninho.ordermanager.owner.domain.vo.Username;
import com.mooninho.ordermanager.store.domain.repository.StoreRepository;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryDetailResponseDto;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryRequestDto;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryTrackingResponseDto;
import com.mooninho.ordermanager.임시.dto.order.CompletedOrderResponseDto;
import com.mooninho.ordermanager.임시.dto.order.OrderTypeResponseDto;
import com.mooninho.ordermanager.임시.dto.order.PreparingOrderResponseDto;
import com.mooninho.ordermanager.임시.dto.order.WaitingOrderResponseDto;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.domain.enums.OrderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId, String username) {

        checkOwner(storeId, getOwnerId(username));

        List<GetWaitingOrderResponseDto> waitingOrders = orderRepository.getWaitingOrders(storeId);
        if (waitingOrders.isEmpty()) {
            throw new EmptyOrderListException();
        }

        return waitingOrders;
    }

//    @Transactional
//    public List<CompletedOrderResponseDto> completeOrdersLookup(Long storeId) {

//        List<CompletedOrderResponseDto> completeOrder = orderQueryRepository.findCompleteOrder(storeId);
//        if (isEmptyCompleteOrder(completeOrder)) {
//            throw new EmptyOrderListException();
//        }
//        return completeOrder;
//    }

    @Transactional
    public void changeOrderStateToPreparing(Long storeId, Long orderId) {

//        OrderStatus orderState = orderQueryRepository.findOrderStateByOrderId(storeId, orderId);
//        if (isNotWaiting(orderState)) {
//            throw new InvalidOrderStateException("주문 대기중 상태에서만 가능한 요청입니다.");
//        }

//        Long updatedRow = orderQueryRepository.updateOrderStateToPreparing(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void changeOrderStateToReady(Long storeId, Long orderId) {

//        OrderStatus orderState = orderQueryRepository.findOrderStateByOrderId(storeId, orderId);
//        if (isNotPreparing(orderState)) {
//            throw new InvalidOrderStateException("주문 준비중 상태에서만 가능한 요청입니다.");
//        }

//        Long updatedRow = orderQueryRepository.updateOrderStateToReady(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void changeOrderStateToCancel(Long storeId, Long orderId) {

//        OrderStatus orderState = orderQueryRepository.findOrderStateByOrderId(storeId, orderId);

//        if (isCancel(orderState)) {
//            throw new InvalidOrderStateException("이미 취소된 주문입니다.");
//        }

//        Long updatedRow = orderQueryRepository.updateOrderStateToCancel(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void changeOrderStateToComplete(Long storeId, Long orderId) {

//        OrderType orderType = orderQueryRepository.findOrderTypeByOrderId(storeId, orderId);
//        if (isNotDelivery(orderType)) {
//            throw new InvalidOrderTypeException();
//        }

//        DeliveryState deliveryState = orderQueryRepository.findDeliveryState(storeId, orderId);
//        if (incompleteDelivery(deliveryState)) {
//            throw new IncompleteDeliveryException();
//        }

//        Long updatedRow = orderQueryRepository.updateOrderStateToComplete(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void toPickup(Long storeId, Long orderId) {

//        OrderTypeResponseDto orderTypes = orderQueryRepository.findOrderTypes(storeId, orderId);
//        if (isNotTogo(orderTypes)) {
//            throw new InvalidOrderTypeException();
//        }
//        if (isNotReady(orderTypes)) {
//            throw new InvalidOrderStateException("주문 준비완료 상태에서만 가능한 요청입니다.");
//        }

//        Long updatedRow = orderQueryRepository.updateOrderStateToComplete(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void createDeliveryInfo(Long orderId, Long storeId, DeliveryRequestDto requestDeliveryDto) {

//        String inputCompanyName = requestDeliveryDto.getCompanyName();
//        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();
//
//        Company companyName = findByCompanyName(inputCompanyName);
//
//        OrderTypeResponseDto orderTypes = orderQueryRepository.findOrderTypes(orderId, storeId);
//        orderTypeValidation(orderTypes);
//
//        Long deliveryCompanyId = orderQueryRepository.findDeliveryCompanyIdByCompanyName(companyName);
//        Delivery delivery = DeliveryDto.toEntity(orderId, deliveryCompanyId, deliveryTips);
//
//        deliveryRepository.save(delivery);
    }

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

    @Transactional(readOnly = true)
    protected Long getOwnerId(String username) {
        return ownerRepository.getOwnerId(Username.of(username))
                .orElseThrow(OwnerNotFoundException::new);
    }

    @Transactional(readOnly = true)
    protected void checkOwner(Long storeId, Long ownerId) {
        boolean owner = storeRepository.isOwner(storeId, ownerId);
        if (!owner) {
            throw new UnauthorizedException();
        }
    }


    private boolean isNotWaiting(OrderStatus orderState) {
        return !orderState.equals(OrderStatus.WAITING);
    }

    private boolean isNotPreparing(OrderStatus orderState) {
        return !orderState.equals(OrderStatus.PREPARING);
    }

    private boolean isNotReady(OrderStatus orderState) {
        return !orderState.equals(OrderStatus.READY);
    }

    private boolean isNotReady(OrderTypeResponseDto orderState) {
        return !orderState.getOrderState().equals(OrderStatus.READY);
    }

    private boolean isCancel(OrderStatus orderState) {
        return orderState.equals(OrderStatus.CANCEL);
    }

    private boolean isNotTogo(OrderTypeResponseDto orderType) {
        return !orderType.getOrderType().equals(OrderType.TOGO);
    }

    private boolean isNotDelivery(OrderType orderType) {
        return !orderType.equals(OrderType.DELIVERY);
    }

    private static boolean incompleteDelivery(DeliveryState deliveryState) {
        return !deliveryState.equals(DeliveryState.COMPLETE);
    }

    private boolean isEmptyWaitingOrder(List<WaitingOrderResponseDto> waitingOrder) {
        return waitingOrder.isEmpty();
    }

    private boolean isEmptyCompleteOrder(List<CompletedOrderResponseDto> completeOrder) {
        return completeOrder.isEmpty();
    }

    private boolean isEmptyPreparingOrder(List<PreparingOrderResponseDto> preparingOrder) {
        return preparingOrder.isEmpty();
    }

    private boolean isEmptyInProgressDelivery(List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList) {
        return fetchedInProgressDeliveryList.isEmpty();
    }

    private boolean isEmptyDeliveryDetail(List<DeliveryDetailResponseDto> deliveryInfo) {
        return deliveryInfo.isEmpty();
    }

    private void orderTypeValidation(OrderTypeResponseDto orderDivision) {
//        if (orderDivision == null) {
//            throw new EmptyOrderListException();
//        }
//        if (isNotDelivery(orderDivision.getOrderType())) {
//            throw new NotDeliveryException();
//        }
//        if (isNotReady(orderDivision.getOrderState())) {
//            throw new NotReadyException();
//        }
    }
}
