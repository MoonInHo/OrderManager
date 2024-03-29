package com.mooninho.ordermanager.ownerapp.order.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.application.dto.request.CreateDeliveryRequestDto;
import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response.GetInProgressDeliveryOrdersResponseDto;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.NotFoundDeliveryCompanyException;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.NotFoundDeliveryException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.ownerapp.exception.exception.order.EmptyOrderListException;
import com.mooninho.ordermanager.ownerapp.exception.exception.order.NotFoundOrderException;
import com.mooninho.ordermanager.ownerapp.exception.exception.owner.OwnerNotFoundException;
import com.mooninho.ordermanager.ownerapp.order.application.event.OrderHasCanceledEvent;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.OrderCancelStrategy;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.OrderCancelStrategyFactory;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.DeliveryAppType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.repository.OrderRepository;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.*;
import com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
import com.mooninho.ordermanager.ownerapp.owner.domain.repository.OwnerRepository;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Username;
import com.mooninho.ordermanager.ownerapp.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;
    private final DeliveryRepository deliveryRepository;
    private final ApplicationEventPublisher eventPublisher; // TODO 서비스 클래스 분할 고민
    private final OrderCancelStrategyFactory orderCancelStrategyFactory;

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

    @Transactional(readOnly = true)
    public GetOrderDetailResponseDto getOrderDetail(Long storeId, Long orderId, String username) {

        checkOwner(storeId, getOwnerId(username));

        return orderRepository.getOrderDetail(orderId)
                .orElseThrow(NotFoundOrderException::new);
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
    public void cancelOrder(
            Long storeId,
            Long orderId,
            CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto,
            String username
    ) {

        checkOwner(storeId, getOwnerId(username));
        validateOrderIsCancel(orderId);

        checkOrderCancellationEligibility(orderId);

        eventPublisher.publishEvent(new OrderHasCanceledEvent(orderId, createOrderCancelHistoryRequestDto));

        orderRepository.changeOrderToCancel(orderId);
    }

    @Transactional
    public void changeTogoOrderToComplete(Long storeId, Long orderId, String username) {

        checkOwner(storeId, getOwnerId(username));
        validateOrderIsReady(getDeliveryId(orderId));

        orderRepository.changeOrderToComplete(orderId);
    }

    @Transactional
    public void createDeliveryRequest(
            Long storeId,
            Long orderId,
            CreateDeliveryRequestDto createDeliveryRequestDto,
            String username
    ) {
        checkOwner(storeId, getOwnerId(username));
        checkDeliveryCompanyExistence(createDeliveryRequestDto);
        validateOrderIsReady(orderId);

        deliveryRepository.save(createDeliveryRequestDto.toEntity(orderId));
    }

    @Transactional(readOnly = true)
    public List<GetInProgressDeliveryOrdersResponseDto> getInProgressDeliveryOrders(Long storeId, String username) {

        checkOwner(storeId, getOwnerId(username));

        List<GetInProgressDeliveryOrdersResponseDto> inProgressDeliveryOrders =
                orderRepository.getInProgressDeliveryOrders(storeId);
        if (inProgressDeliveryOrders.isEmpty()) {
            throw new EmptyOrderListException();
        }

        return inProgressDeliveryOrders;
    }

    @Transactional
    public void changeDeliveryOrderToComplete(Long storeId, Long orderId, String username) {

        checkOwner(storeId, getOwnerId(username));
        validateDeliveryIsComplete(orderId);

        orderRepository.changeOrderToComplete(orderId);
    }

    @Transactional(readOnly = true)
    public GetDeliveryOrderResponseDto getDeliveryOrder(Long storeId, Long deliveryId, String username) {

        checkOwner(storeId, getOwnerId(username));

        return orderRepository.getDeliveryOrder(storeId, deliveryId)
                .orElseThrow(NotFoundDeliveryException::new);
    }

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

    @Transactional(readOnly = true)
    protected DeliveryAppType getDeliveryAppType(Long orderId) {
        return orderRepository.getDeliveryAppType(orderId);
    }

    @Transactional(readOnly = true)
    protected Long getDeliveryId(Long orderId) {
        return deliveryRepository.getDeliveryId(orderId);
    }

    @Transactional(readOnly = true)
    protected void checkDeliveryCompanyExistence(CreateDeliveryRequestDto createDeliveryRequestDto) {
        boolean existCompany = deliveryRepository.isExistCompany(createDeliveryRequestDto.getDeliveryCompanyId());
        if (!existCompany) {
            throw new NotFoundDeliveryCompanyException();
        }
    }

    @Transactional(readOnly = true)
    protected void validateDeliveryIsComplete(Long deliveryId) {

        boolean deliveryComplete = deliveryRepository.isDeliveryComplete(deliveryId);
        if (!deliveryComplete) {
            throw new InvalidRequestException();
        }
    }

    private void checkOrderCancellationEligibility(Long orderId) {

        OrderCancelStrategy strategy = orderCancelStrategyFactory.getStrategy(getDeliveryAppType(orderId));

        boolean isOrderCancellationAllowed = strategy.checkOrderCancellationEligibility(getDeliveryId(orderId));
        if (!isOrderCancellationAllowed) {
            throw new InvalidRequestException();
        }
    }

    private void validateOrderIsWaiting(Long orderId) {
        if (isNotOrderStatusWaiting(getOrderStatus(orderId))) {
            throw new InvalidRequestException();
        }
    }

    private void validateOrderIsPreparing(Long orderId) {
        if (isNotOrderStatusPreparing(getOrderStatus(orderId))) {
            throw new InvalidRequestException();
        }
    }

    private void validateOrderIsCancel(Long orderId) {
        if (isOrderStatusCancel(getOrderStatus(orderId))) {
            throw new InvalidRequestException();
        }
    }

    private void validateOrderIsReady(Long orderId) {
        if (isNotOrderStatusReady(getOrderStatus(orderId))) {
            throw new InvalidRequestException();
        }
    }

    private boolean isNotOrderStatusWaiting(OrderStatus orderStatus) {
        return !orderStatus.equals(OrderStatus.WAITING);
    }

    private boolean isNotOrderStatusPreparing(OrderStatus orderStatus) {
        return !orderStatus.equals(OrderStatus.PREPARING);
    }

    private boolean isNotOrderStatusReady(OrderStatus orderStatus) {
        return !orderStatus.equals(OrderStatus.READY);
    }

    private boolean isOrderStatusCancel(OrderStatus orderStatus) {
        return orderStatus.equals(OrderStatus.CANCEL);
    }
}
