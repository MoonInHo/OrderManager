package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.delivery.RequestDeliveryDto;
import com.foodtech.mate.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/create-delivery-info")
    public void createDeliveryInfo(@RequestBody RequestDeliveryDto requestDeliveryDto) {

        Long orderId = requestDeliveryDto.getOrderId();
        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();

        deliveryService.createDeliveryInfo(orderId, deliveryTips);
    }

    @PutMapping("/delivery-driver-assignment")
    public ResponseEntity<String> deliveryDriverAssignment(@RequestBody RequestDeliveryDto requestDeliveryDto) {

        Long deliveryId = requestDeliveryDto.getDeliveryId();
        Long deliveryDriverId = requestDeliveryDto.getDeliveryDriverId();

        Long update = deliveryService.deliveryDriverAssignment(deliveryId, deliveryDriverId);
        if (update == null) {
            return ResponseEntity.badRequest().body("기사 배정에 실패하였습니다.");
        }
        return ResponseEntity.ok("기사가 배정되었습니다.");
    }
}
