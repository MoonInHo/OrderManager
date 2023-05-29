package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.domain.entity.DeliveryCompany;
import com.foodtech.mate.domain.entity.Order;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.foodtech.mate.dto.delivery.*;
import com.foodtech.mate.dto.order.CompletedOrderResponseDto;
import com.foodtech.mate.dto.order.PreparingOrderResponseDto;
import com.foodtech.mate.dto.order.WaitingOrderResponseDto;
import com.foodtech.mate.enums.state.OrderState;
import com.foodtech.mate.enums.type.OrderType;
import com.foodtech.mate.exception.exception.EmptyResultDataAccessException;
import com.foodtech.mate.exception.exception.delivery.EmptyDeliveryListException;
import com.foodtech.mate.exception.exception.delivery.NotDeliveryException;
import com.foodtech.mate.exception.exception.delivery.NotReadyException;
import com.foodtech.mate.exception.exception.order.EmptyOrderListException;
import com.foodtech.mate.exception.exception.order.InvalidOrderTypeException;
import com.foodtech.mate.repository.DeliveryRepository;
import com.foodtech.mate.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.foodtech.mate.domain.wrapper.delivery.Company.findByCompanyName;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final DeliveryRepository deliveryRepository;
    private final OrderQueryRepository orderQueryRepository;

    @Transactional
    public List<WaitingOrderResponseDto> findWaitingOrders() {

        List<WaitingOrderResponseDto> waitingOrder = orderQueryRepository.findWaitingOrder();
        if (isNullAndEmptyWaitingOrder(waitingOrder)) {
            throw new EmptyOrderListException();
        }
        return waitingOrder;
    }

    @Transactional
    public List<PreparingOrderResponseDto> findPreparingOrders() {

        List<PreparingOrderResponseDto> preparingOrder = orderQueryRepository.findPreparingOrder();
        if (isNullAndEmptyPreparingOrder(preparingOrder)) {
            throw new EmptyOrderListException();
        }
        return preparingOrder;
    }

    @Transactional
    public List<CompletedOrderResponseDto> completeOrdersLookup() {

        List<CompletedOrderResponseDto> completeOrder = orderQueryRepository.findCompleteOrder();
        if (isNullAndEmptyCompleteOrder(completeOrder)) {
            throw new EmptyOrderListException();
        }
        return completeOrder;
    }

    @Transactional
    public OrderState findOrderState(Long orderId) {
        OrderState orderState = orderQueryRepository.findOrderStateByOrderId(orderId);
        if (orderState == null) {
            throw new EmptyOrderListException();
        }
        return orderState;
    }

    @Transactional
    public void changeOrderState(Long orderId, OrderState orderStateCode) {
        Long updatedRow = orderQueryRepository.updateOrderState(orderId, orderStateCode);
        if (updatedRow == null) {
            throw new EmptyResultDataAccessException();
        }
    }

    @Transactional
    public void changeOrderStateToPickUp(Long orderId, OrderState orderStateCode) {

        OrderType orderType = orderQueryRepository.findOrderTypeByOrderId(orderId);
        if (isNotTogo(orderType)) {
            throw new InvalidOrderTypeException();
        }
        orderQueryRepository.updateOrderState(orderId, orderStateCode);
    }

    @Transactional
    public void createDeliveryInfo(Long orderId, DeliveryRequestDto requestDeliveryDto) {

        String inputCompanyName = requestDeliveryDto.getCompanyName();
        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();

        Company companyName = findByCompanyName(inputCompanyName);

        Order foundOrder = orderQueryRepository.findOrderByOrderId(orderId);
        if (foundOrder == null) {
            throw new EmptyOrderListException();
        }
        if (isNotDelivery(foundOrder.getOrderType())) {
            throw new NotDeliveryException();
        }
        if (isNotReady(foundOrder.getOrderState())) {
            throw new NotReadyException();
        }

        DeliveryCompany deliveryCompany = orderQueryRepository.findDeliveryCompanyByCompanyName(companyName);
        Delivery delivery = DeliveryDto.toEntity(foundOrder, deliveryCompany, deliveryTips);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery() {

        List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList = orderQueryRepository.findDeliveryByDeliveryState();
        if (isNullAndEmptyInProgressDelivery(fetchedInProgressDeliveryList)) {
            throw new EmptyDeliveryListException();
        }
        return fetchedInProgressDeliveryList;
    }

    @Transactional
    public List<DeliveryDetailResponseDto> deliveryDetailLookup(Long deliveryId) {

        List<DeliveryDetailResponseDto> deliveryInfo = orderQueryRepository.findDeliveryDetail(deliveryId);
        if (isNullAndEmptyDeliveryDetail(deliveryInfo)) {
            throw new EmptyDeliveryListException();
        }
        return deliveryInfo;
    }

    private static boolean isNullAndEmptyWaitingOrder(List<WaitingOrderResponseDto> waitingOrder) {
        return waitingOrder == null || waitingOrder.isEmpty();
    }

    private static boolean isNullAndEmptyCompleteOrder(List<CompletedOrderResponseDto> completeOrder) {
        return completeOrder == null || completeOrder.isEmpty();
    }

    private static boolean isNullAndEmptyPreparingOrder(List<PreparingOrderResponseDto> preparingOrder) {
        return preparingOrder == null || preparingOrder.isEmpty();
    }

    private static boolean isNullAndEmptyInProgressDelivery(List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList) {
        return fetchedInProgressDeliveryList == null || fetchedInProgressDeliveryList.isEmpty();
    }

    private static boolean isNullAndEmptyDeliveryDetail(List<DeliveryDetailResponseDto> deliveryInfo) {
        return deliveryInfo == null || deliveryInfo.isEmpty();
    }

    private boolean isNotTogo(OrderType orderType) {
        return !orderType.equals(OrderType.TOGO);
    }

    private boolean isNotDelivery(OrderType orderType) {
        return !orderType.equals(OrderType.DELIVERY);
    }

    private boolean isNotReady(OrderState orderState) {
        return !orderState.equals(OrderState.READY);
    }
}
