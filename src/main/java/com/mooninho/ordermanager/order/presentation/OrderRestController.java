package com.mooninho.ordermanager.order.presentation;

import com.mooninho.ordermanager.order.application.service.OrderService;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/{storeId}/waiting")
    public ResponseEntity<List<GetWaitingOrderResponseDto>> getWaitingOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getWaitingOrders(storeId, authentication.getName()));
    }

    @GetMapping("/{storeId}/preparing")
    public ResponseEntity<List<GetPreparingOrderResponseDto>> getPreparingOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getPreparingOrders(storeId, authentication.getName()));
    }

    @GetMapping("/{storeId}/complete")
    public ResponseEntity<List<GetCompleteOrderResponseDto>> getCompleteOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getCompleteOrders(storeId, authentication.getName()));
    }
//
//    @PatchMapping("/{orderId}/accept")
//    public ResponseEntity<ResponseMessageDto> acceptOrders(@PathVariable Long orderId) {
//
//        Long storeId = orderService.findStoreId();
//
//        orderService.changeOrderStateToPreparing(storeId, orderId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(APPLICATION_JSON)
//                .body(new ResponseMessageDto("주문이 수락되었습니다"));
//    }
//
//    @PatchMapping("/{orderId}/ready")
//    public ResponseEntity<ResponseMessageDto> readyOrders(@PathVariable Long orderId) {
//
//        Long storeId = orderService.findStoreId();
//
//        orderService.changeOrderStateToReady(storeId, orderId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(APPLICATION_JSON)
//                .body(new ResponseMessageDto("메뉴가 준비되었습니다."));
//    }
//
//    @PatchMapping("/{orderId}/cancel")
//    public ResponseEntity<ResponseMessageDto> cancelOrder(@PathVariable Long orderId) {
//
//        Long storeId = orderService.findStoreId();
//
//        orderService.changeOrderStateToCancel(storeId, orderId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(new ResponseMessageDto("주문이 취소되었습니다"));
//    }
//
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
//    @PatchMapping("/{orderId}/pickup")
//    public ResponseEntity<ResponseMessageDto> pickUpComplete(@PathVariable Long orderId) {
//
//        Long storeId = orderService.findStoreId();
//
//        orderService.toPickup(storeId, orderId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(APPLICATION_JSON)
//                .body(new ResponseMessageDto("픽업이 완료되었습니다."));
//    }
//
//    @PostMapping("/{orderId}/deliveries")
//    public ResponseEntity<ResponseMessageDto> createDeliveryRequest(@PathVariable Long orderId, @RequestBody DeliveryRequestDto DeliveryRequestDto) {
//
//        Long storeId = orderService.findStoreId();
//
//        orderService.createDeliveryInfo(orderId, storeId, DeliveryRequestDto);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .contentType(APPLICATION_JSON)
//                .body(new ResponseMessageDto("배달원 배정을 요청했습니다"));
//    }
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