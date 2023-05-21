package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.order.CompletedOrderDto;
import com.foodtech.mate.domain.dto.order.FindOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.exception.exception.NoOrderException;
import com.foodtech.mate.repository.OrderQueryRepository;
import com.foodtech.mate.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @Transactional
    public List<FindOrderDto> findOrder(Long storeId, OrderState orderStateCode) {

        List<FindOrderDto> foundOrder = orderQueryRepository.findOrderByStoreId(storeId, orderStateCode);
        if (foundOrder == null) {
            throw new NoOrderException("대기중인 주문이 없습니다.");
        }
        return foundOrder;
    }

    @Transactional
    public OrderState findOrderState(Long orderId) {
        return orderQueryRepository.findOrderStateByOrderId(orderId);
    }

    @Transactional
    public void changeOrderState(Long orderId, OrderState orderStateCode) {
        orderQueryRepository.changeOrderState(orderId, orderStateCode);
    }

    public void checkOrderType(Long orderId) {

        OrderType orderType = orderQueryRepository.findOrderTypeByOrderId(orderId);
        if (!orderType.equals(OrderType.TOGO)) {
            throw new IllegalArgumentException("올바르지 않은 요청입니다.");
        }
    }
}
