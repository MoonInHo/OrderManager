package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.order.PendingOrderDto;
import com.foodtech.mate.exception.exception.NoOrderException;
import com.foodtech.mate.repository.OrderQueryRepository;
import com.foodtech.mate.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public List<PendingOrderDto> findPendingOrder(Long storeId) {

        List<PendingOrderDto> fountPendingOrders = orderQueryRepository.findPendingOrderByStoreId(storeId);
        if (fountPendingOrders == null) {
            throw new NoOrderException("대기중인 주문이 없습니다.");
        }
        return fountPendingOrders;
    }
}
