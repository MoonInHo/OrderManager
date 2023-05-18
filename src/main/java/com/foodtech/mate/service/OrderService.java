package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.order.FindOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.wrapper.account.QUserId;
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
    public List<FindOrderDto> findWaitingOrder(Long storeId, OrderState orderStateCode) {

        List<FindOrderDto> fountWaitingOrders = orderQueryRepository.findOrderByStoreId(storeId, orderStateCode);
        if (fountWaitingOrders == null) {
            throw new NoOrderException("대기중인 주문이 없습니다.");
        }
        return fountWaitingOrders;
    }

    @Transactional
    public OrderState checkOrderState(Long orderId) {
        return orderQueryRepository.findOrderByOrderId(orderId);
    }

    @Transactional
    public void changeOrderState(Long orderId, OrderState orderStateCode) {
        orderQueryRepository.changeOrderState(orderId, orderStateCode);
    }
}
