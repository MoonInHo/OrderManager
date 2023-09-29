package com.mooninho.ordermanager.ownerapp.delivery.presentation;

import com.mooninho.ordermanager.ownerapp.delivery.application.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{storeId}")
@RequiredArgsConstructor
public class DeliveryRestController {

    private final DeliveryService deliveryService;

//    @PatchMapping("/{deliveryId}/{deliveryDriverId}/assignment")
//    public ResponseEntity<Void> deliveryDriverAssignment(
//            @PathVariable Long deliveryId,
//            @PathVariable Long deliveryDriverId
//    ) {
//
//        deliveryService.deliveryDriverAssignment(deliveryId, deliveryDriverId);
//
//        return ResponseEntity.ok().build();
//    }

//    @PatchMapping("/{deliveryId}/{deliveryDriverId}/pick-up")
//    public ResponseEntity<ResponseMessageDto> deliveryPickUp(@PathVariable Long deliveryId, @PathVariable Long deliveryDriverId) {
//
//        deliveryService.deliveryPickUp(deliveryId, deliveryDriverId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(new ResponseMessageDto("배달원이 물품을 픽업했습니다"));
//    }
//
//    @PatchMapping("/{deliveryId}/{deliveryDriverId}/complete")
//    public ResponseEntity<ResponseMessageDto> deliveryComplete(@PathVariable Long deliveryId, @PathVariable Long deliveryDriverId) {
//
//        deliveryService.deliveryComplete(deliveryId, deliveryDriverId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(new ResponseMessageDto("배달원이 배달을 완료했습니다"));
//    }
}
