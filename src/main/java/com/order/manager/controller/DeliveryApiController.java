package com.order.manager.controller;

import com.order.manager.dto.ResponseMessageDto;
import com.order.manager.dto.ResponseStatusDto;
import com.order.manager.dto.delivery.DeliveryRequestDto;
import com.order.manager.enums.state.DeliveryState;
import com.order.manager.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryApiController {

    private final DeliveryService deliveryService;

    @PatchMapping("/{deliveryId}/{deliveryDriverId}/assignment")
    public ResponseEntity<ResponseMessageDto> deliveryDriverAssignment(@PathVariable Long deliveryId, @PathVariable Long deliveryDriverId) {

        deliveryService.deliveryDriverAssignment(deliveryId, deliveryDriverId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("배달원이 배정 되었습니다."));
    }

    @PatchMapping("/{deliveryId}/{deliveryDriverId}/pick-up")
    public ResponseEntity<ResponseMessageDto> deliveryPickUp(@PathVariable Long deliveryId, @PathVariable Long deliveryDriverId) {

        deliveryService.deliveryPickUp(deliveryId, deliveryDriverId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("배달원이 물품을 픽업했습니다"));
    }

    @PatchMapping("/{deliveryId}/{deliveryDriverId}/complete")
    public ResponseEntity<ResponseMessageDto> deliveryComplete(@PathVariable Long deliveryId, @PathVariable Long deliveryDriverId) {

        deliveryService.deliveryComplete(deliveryId, deliveryDriverId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("배달원이 배달을 완료했습니다"));
    }
}
