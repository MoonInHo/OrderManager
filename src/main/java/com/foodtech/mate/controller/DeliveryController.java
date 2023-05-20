package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.delivery.RequestDeliveryDto;
import com.foodtech.mate.service.DeliveryService;
import com.foodtech.mate.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final OrderService orderService;
    private final DeliveryService deliveryService;

    @PostMapping("/create-delivery-info")
    public void createDeliveryInfo(@RequestBody RequestDeliveryDto requestDeliveryDto) {

        Long orderId = requestDeliveryDto.getOrderId();
        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();

        deliveryService.createDeliveryInfo(orderId, deliveryTips);
    }
}
