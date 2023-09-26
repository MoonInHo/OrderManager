package com.mooninho.ordermanager.order.application.service;

import com.mooninho.ordermanager.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.exception.exception.order.EmptyOrderListException;
import com.mooninho.ordermanager.exception.exception.order.InvalidOrderStatusException;
import com.mooninho.ordermanager.exception.exception.order.NotFoundOrderException;
import com.mooninho.ordermanager.exception.exception.owner.OwnerNotFoundException;
import com.mooninho.ordermanager.order.application.dto.request.CreateOrderCancelHistoryRequestDto;
import com.mooninho.ordermanager.order.application.event.OrderHasCanceledEvent;
import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.domain.repository.OrderCancelHistoryRepository;
import com.mooninho.ordermanager.order.domain.repository.OrderRepository;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;
import com.mooninho.ordermanager.owner.domain.repository.OwnerRepository;
import com.mooninho.ordermanager.owner.domain.vo.Username;
import com.mooninho.ordermanager.store.domain.repository.StoreRepository;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCancelHistoryRepository orderCancelHistoryRepository;
    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;
    private final ApplicationEventPublisher eventPublisher; // TODO 서비스 클래스 분할 고민

    @Transactional(readOnly = true)
    public List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId, String username) {

        checkOwner(storeId, getOwnerId(username));

        List<GetWaitingOrderResponseDto> waitingOrders = orderRepository.getWaitingOrders(storeId);
        if (waitingOrders.isEmpty()) {
            throw new EmptyOrderListException(); //TODO 예외 핸들러에서 catch 하는데 에러코드와 메세지를 반환하지 않는 이유 찾기
        }
        return waitingOrders;
    }

    @Transactional(readOnly = true)
    public List<GetPreparingOrderResponseDto> getPreparingOrders(Long storeId, String username) {

        checkOwner(storeId, getOwnerId(username));

        List<GetPreparingOrderResponseDto> preparingOrders = orderRepository.getPreparingOrders(storeId);
        if (preparingOrders.isEmpty()) {
            throw new EmptyOrderListException();
        }
        return preparingOrders;
    }

    @Transactional(readOnly = true)
    public List<GetCompleteOrderResponseDto> getCompleteOrders(Long storeId, String username) {

        checkOwner(storeId, getOwnerId(username));

        List<GetCompleteOrderResponseDto> completeOrder = orderRepository.getCompleteOrders(storeId);
        if (completeOrder.isEmpty()) {
            throw new EmptyOrderListException();
        }
        return completeOrder;
    }

    @Transactional
    public void changeOrderToPreparing(Long storeId, Long orderId, String username) {

        checkOwner(storeId, getOwnerId(username));
        validateOrderIsWaiting(orderId);

        orderRepository.changeOrderToPreparing(orderId); // TODO 업데이트 실패 처리 로직 고민
    }

    @Transactional
    public void changeOrderToReady(Long storeId, Long orderId, String username) {

        checkOwner(storeId, getOwnerId(username));
        validateOrderIsPreparing(orderId);

        orderRepository.changeOrderToReady(orderId);
    }

    @Transactional
    public void changeOrderToCancel( // TODO 파라미터 갯수 고민
            Long storeId,
            Long orderId,
            String username,
            CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto
    ) {

        checkOwner(storeId, getOwnerId(username));
        validateOrderIsCancel(orderId);

        eventPublisher.publishEvent(new OrderHasCanceledEvent(orderId, createOrderCancelHistoryRequestDto)); // TODO 이벤트 수행 실패시 재시도 방법 고민

        orderRepository.changeOrderToCancel(orderId);
    }

    @Transactional
    public void createCancelHistory(
            Long orderId,
            CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto
    ) {
        orderCancelHistoryRepository.save(createOrderCancelHistoryRequestDto.toEntity(orderId));
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

    @Transactional(readOnly = true)
    protected OrderStatus getOrderStatus(Long orderId) {
        return orderRepository.getOrderStatus(orderId)
                .orElseThrow(NotFoundOrderException::new);
    }
    
    private void validateOrderIsWaiting(Long orderId) {
        if (isNotOrderStatusWaiting(getOrderStatus(orderId))) {
            throw new InvalidOrderStatusException();
        }
    }

    private void validateOrderIsPreparing(Long orderId) {
        if (isNotOrderStatusPreparing(getOrderStatus(orderId))) {
            throw new InvalidOrderStatusException();
        }
    }

    private void validateOrderIsCancel(Long orderId) {
        if (isOrderStatusCancel(getOrderStatus(orderId))) {
            throw new InvalidOrderStatusException();
        }
    }

    private boolean isNotOrderStatusWaiting(OrderStatus orderStatus) {
        return !orderStatus.equals(OrderStatus.WAITING);
    }

    private boolean isNotOrderStatusPreparing(OrderStatus orderStatus) {
        return !orderStatus.equals(OrderStatus.PREPARING);
    }

    private boolean isOrderStatusCancel(OrderStatus orderStatus) {
        return orderStatus.equals(OrderStatus.CANCEL);
    }
}
