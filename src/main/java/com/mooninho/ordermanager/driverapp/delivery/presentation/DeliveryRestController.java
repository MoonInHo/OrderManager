package com.mooninho.ordermanager.driverapp.delivery.presentation;

import com.mooninho.ordermanager.driverapp.delivery.application.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveries/{driverId}")
@RequiredArgsConstructor
public class DeliveryRestController {

    private final DeliveryService deliveryService;

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
//    @GetMapping("/deliveries")
//    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery() {
//
//        Long storeId = orderService.findStoreId();
//
//        return orderService.lookupInProgressDelivery(storeId);
//    }
//
//    @GetMapping("/deliveries/{deliveryId}/detail")
//    public List<DeliveryDetailResponseDto> deliveryDetailInfoLookup(@PathVariable Long deliveryId) {
//
//        Long storeId = orderService.findStoreId();
//
//        return orderService.deliveryDetailLookup(storeId, deliveryId);
//    }

}
