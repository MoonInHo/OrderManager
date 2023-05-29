package com.foodtech.mate.controller;

import com.foodtech.mate.dto.delivery.DeliveryRequestDto;
import com.foodtech.mate.enums.state.DeliveryState;
import com.foodtech.mate.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryApiController {

    private final DeliveryService deliveryService;

    @PatchMapping("/{deliveryId}/assignment")
    public ResponseEntity<String> deliveryDriverAssignment(@PathVariable Long deliveryId, @RequestBody DeliveryRequestDto deliveryRequestDto) {

        deliveryService.deliveryDriverAssignment(deliveryId, deliveryRequestDto);

        return ResponseEntity.ok("배달원 배정되었습니다.");
    }

    @PatchMapping("/{deliveryId}/pick-up")
    public ResponseEntity<String> deliveryPickUp(@PathVariable Long deliveryId, @RequestBody DeliveryRequestDto requestDeliveryDto) {

        Long deliveryDriverId = requestDeliveryDto.getDeliveryDriverId();

        deliveryService.deliveryPickUp(deliveryId, deliveryDriverId, DeliveryState.PICKUP);

        return ResponseEntity.ok("배달원이 물품을 픽업했습니다");
    }

    @PatchMapping("/{deliveryId}/complete")
    public ResponseEntity<String> deliveryComplete(@PathVariable Long deliveryId, @RequestBody DeliveryRequestDto requestDeliveryDto) {

        Long deliveryDriverId = requestDeliveryDto.getDeliveryDriverId();

        deliveryService.deliveryComplete(deliveryId, deliveryDriverId, DeliveryState.COMPLETE);

        return ResponseEntity.ok("배달원이 배달을 완료했습니다");
    }
}
