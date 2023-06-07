package com.order.manager.service;

import com.order.manager.domain.entity.Delivery;
import com.order.manager.domain.entity.DeliveryCompany;
import com.order.manager.domain.entity.Order;
import com.order.manager.domain.wrapper.delivery.Company;
import com.order.manager.dto.delivery.*;
import com.order.manager.dto.order.CompletedOrderResponseDto;
import com.order.manager.dto.order.PreparingOrderResponseDto;
import com.order.manager.dto.order.WaitingOrderResponseDto;
import com.order.manager.enums.state.OrderState;
import com.order.manager.enums.type.OrderType;
import com.order.manager.exception.exception.EmptyResultDataAccessException;
import com.order.manager.exception.exception.delivery.EmptyDeliveryListException;
import com.order.manager.exception.exception.delivery.NotDeliveryException;
import com.order.manager.exception.exception.delivery.NotReadyException;
import com.order.manager.exception.exception.order.EmptyOrderListException;
import com.order.manager.exception.exception.order.InvalidOrderTypeException;
import com.order.manager.repository.DeliveryRepository;
import com.order.manager.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.order.manager.domain.wrapper.delivery.Company.findByCompanyName;

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

        //TODO order 와 delivery 객체를 전달하는 것이아닌 키값만 전달하는 방법으로 변경해서 구현
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

    private boolean isNullAndEmptyWaitingOrder(List<WaitingOrderResponseDto> waitingOrder) {
        return waitingOrder == null || waitingOrder.isEmpty();
    }

    private boolean isNullAndEmptyCompleteOrder(List<CompletedOrderResponseDto> completeOrder) {
        return completeOrder == null || completeOrder.isEmpty();
    }

    private boolean isNullAndEmptyPreparingOrder(List<PreparingOrderResponseDto> preparingOrder) {
        return preparingOrder == null || preparingOrder.isEmpty();
    }

    private boolean isNullAndEmptyInProgressDelivery(List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList) {
        return fetchedInProgressDeliveryList == null || fetchedInProgressDeliveryList.isEmpty();
    }

    private boolean isNullAndEmptyDeliveryDetail(List<DeliveryDetailResponseDto> deliveryInfo) {
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
