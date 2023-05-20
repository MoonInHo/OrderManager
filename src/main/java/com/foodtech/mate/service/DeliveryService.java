package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.delivery.DeliveryDto;
import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.domain.entity.Order;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.repository.DeliveryQueryRepository;
import com.foodtech.mate.repository.DeliveryRepository;
import com.foodtech.mate.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderQueryRepository orderQueryRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryQueryRepository deliveryQueryRepository;

    public void createDeliveryInfo(Long orderId, Integer deliveryTips) {

        Order foundOrder = orderQueryRepository.findOrderByOrderId(orderId);
        if (!foundOrder.getOrderState().equals(OrderState.READY)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        Delivery delivery = DeliveryDto.toEntity(foundOrder, deliveryTips);

        deliveryRepository.save(delivery);
    }
}
