package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.order.CompletedOrderDto;
import com.foodtech.mate.domain.dto.order.PreparingOrderDto;
import com.foodtech.mate.domain.dto.order.WaitingOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.exception.exception.NoOrderException;
import com.foodtech.mate.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderQueryRepository orderQueryRepository;

    @Transactional
    public List<WaitingOrderDto> findWaitingOrders() {

        List<WaitingOrderDto> waitingOrder = orderQueryRepository.findWaitingOrder();
        if (waitingOrder == null) {
            throw new NoOrderException("대기중인 주문이 없습니다.");
        }
        return waitingOrder;
    }

    @Transactional
    public List<PreparingOrderDto> findPreparingOrders() {

        List<PreparingOrderDto> preparingOrder = orderQueryRepository.findPreparingOrder();
        if (preparingOrder == null) {
            throw new NoOrderException("준비중인 주문이 없습니다.");
        }
        return preparingOrder;
    }

    @Transactional
    public List<CompletedOrderDto> completeOrdersLookup() {

        List<CompletedOrderDto> completeOrder = orderQueryRepository.findCompleteOrder();
        if (completeOrder == null) {
            throw new NoOrderException("완료된 주문이 없습니다.");
        }
        return completeOrder;
    }

    @Transactional
    public OrderState findOrderState(Long orderId) {
        return orderQueryRepository.findOrderStateByOrderId(orderId);
    }

    @Transactional
    public void changeOrderState(Long orderId, OrderState orderStateCode) {
        orderQueryRepository.updateOrderState(orderId, orderStateCode);
    }

    @Transactional
    public void changeOrderStateToPickUp(Long orderId, OrderState orderState) {

        OrderType orderType = orderQueryRepository.findOrderTypeByOrderId(orderId);
        if (!orderType.equals(OrderType.TOGO)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
        orderQueryRepository.updateOrderState(orderId, orderState);
    }
}
