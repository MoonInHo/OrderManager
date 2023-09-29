package com.mooninho.ordermanager.ownerapp.delivery.presentation;

import com.mooninho.ordermanager.ownerapp.delivery.application.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryRestController {  // TODO 배달 기사용 앱 분리 고민

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

//    @PatchMapping("/deliveries/{orderId}/complete")
//    public ResponseEntity<ResponseMessageDto> completeDeliveryOrder(@PathVariable Long orderId) {
//
//        Long storeId = orderService.findStoreId();
//
//        orderService.changeOrderStateToComplete(storeId, orderId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(APPLICATION_JSON)
//                .body(new ResponseMessageDto("배달주문 처리가 완료되었습니다."));
//    }
//
//

//
//    @GetMapping("/deliveries/{deliveryId}/detail")
//    public List<DeliveryDetailResponseDto> deliveryDetailInfoLookup(@PathVariable Long deliveryId) {
//
//        Long storeId = orderService.findStoreId();
//
//        return orderService.deliveryDetailLookup(storeId, deliveryId);
//    }
}
