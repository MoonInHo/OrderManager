package com.mooninho.ordermanager.ownerapp.order.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.application.dto.request.CreateDeliveryRequestDto;
import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response.GetInProgressDeliveryOrdersResponseDto;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.DeliveryCompanyNotFoundException;
import com.mooninho.ordermanager.ownerapp.exception.exception.delivery.DeliveryOrderNotFoundException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.ownerapp.exception.exception.order.NotFoundOrderException;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;
import com.mooninho.ordermanager.ownerapp.order.application.event.OrderHasCanceledEvent;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.OrderCancelStrategy;
import com.mooninho.ordermanager.ownerapp.order.application.strategy.OrderCancelStrategyFactory;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.DeliveryAppType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.repository.OrderRepository;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.*;
import com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
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
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final DeliveryRepository deliveryRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final OrderCancelStrategyFactory orderCancelStrategyFactory;

    @Transactional(readOnly = true)
    public List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId, String username) {

        checkOwnership(storeId, getMemberId(username));

        return orderRepository.getWaitingOrders(storeId);
    }

    @Transactional(readOnly = true)
    public List<GetPreparingOrderResponseDto> getPreparingOrders(Long storeId, String username) {

        checkOwnership(storeId, getMemberId(username));

        return orderRepository.getPreparingOrders(storeId);
    }

    @Transactional(readOnly = true)
    public List<GetCompletedOrderResponseDto> getCompletedOrders(Long storeId, String username) {

        checkOwnership(storeId, getMemberId(username));

        return orderRepository.getCompleteOrders(storeId);
    }

    @Transactional(readOnly = true)
    public GetOrderDetailResponseDto getOrderDetail(Long storeId, Long orderId, String username) {

        checkOwnership(storeId, getMemberId(username));

        return orderRepository.getOrderDetail(orderId)
                .orElseThrow(NotFoundOrderException::new);
    }

    @Transactional
    public void changeOrderToPreparing(Long storeId, Long orderId, String username) {

        checkOwnership(storeId, getMemberId(username));
        validateOrderIsWaiting(orderId);

        orderRepository.changeOrderToPreparing(orderId); // TODO 업데이트 실패 처리 로직 고민
    }

    @Transactional
    public void changeOrderToReady(Long storeId, Long orderId, String username) {

        checkOwnership(storeId, getMemberId(username));
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
        checkOwnership(storeId, getMemberId(username));
        validateOrderIsCancel(orderId);

        checkOrderCancellationEligibility(orderId);

        eventPublisher.publishEvent(
                new OrderHasCanceledEvent(
                        orderId,
                        createOrderCancelHistoryRequestDto
                )
        );
        orderRepository.changeOrderToCancel(orderId);
    }

    @Transactional
    public void changeTogoOrderToComplete(Long storeId, Long orderId, String username) {

        checkOwnership(storeId, getMemberId(username));
        validateOrderIsReady(orderId);

        orderRepository.changeOrderToComplete(orderId);
    }

    @Transactional
    public void createDeliveryRequest(
            Long storeId,
            Long orderId,
            CreateDeliveryRequestDto createDeliveryRequestDto,
            String username
    ) {
        checkOwnership(storeId, getMemberId(username));
        checkDeliveryCompanyExistence(createDeliveryRequestDto);
        validateOrderIsReady(orderId);

        deliveryRepository.save(createDeliveryRequestDto.toEntity(orderId));
    }

    @Transactional(readOnly = true)
    public List<GetInProgressDeliveryOrdersResponseDto> getInProgressDeliveryOrders(Long storeId, String username) {

        checkOwnership(storeId, getMemberId(username));

        return orderRepository.getInProgressDeliveryOrders(storeId);
    }

    @Transactional
    public void changeDeliveryOrderToComplete(Long storeId, Long orderId, String username) {

        checkOwnership(storeId, getMemberId(username));
        validateDeliveryIsCompleted(orderId);

        orderRepository.changeOrderToComplete(orderId);
    }

    @Transactional(readOnly = true)
    public GetDeliveryOrderResponseDto getDeliveryOrder(Long storeId, Long deliveryId, String username) {

        checkOwnership(storeId, getMemberId(username));

        return orderRepository.getDeliveryOrder(storeId, deliveryId)
                .orElseThrow(DeliveryOrderNotFoundException::new);
    }

    private Long getMemberId(String username) {
        return memberRepository.getMemberId(Username.of(username));
    }

    private void checkOwnership(Long storeId, Long memberId) {
        boolean owner = storeRepository.isOwner(storeId, memberId);
        if (!owner) {
            throw new UnauthorizedException();
        }
    }

    private OrderStatus getOrderStatus(Long orderId) {
        return orderRepository.getOrderStatus(orderId)
                .orElseThrow(NotFoundOrderException::new);
    }

    private DeliveryAppType getDeliveryAppType(Long orderId) {
        return orderRepository.getDeliveryAppType(orderId);
    }

    private Long getDeliveryId(Long orderId) {
        return deliveryRepository.getDeliveryId(orderId);
    }

    private void checkDeliveryCompanyExistence(CreateDeliveryRequestDto createDeliveryRequestDto) {
        boolean existCompany = deliveryRepository.isCompanyExist(createDeliveryRequestDto.getDeliveryCompanyId());
        if (!existCompany) {
            throw new DeliveryCompanyNotFoundException();
        }
    }

    private void validateDeliveryIsCompleted(Long deliveryId) {
        boolean deliveryComplete = deliveryRepository.isDeliveryCompleted(deliveryId);
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
