package com.mooninho.ordermanager.order.presentation;

import com.mooninho.ordermanager.order.application.dto.request.CreateOrderCancelHistoryRequestDto;
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
@RequestMapping("/api/{storeId}/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/waiting")
    public ResponseEntity<List<GetWaitingOrderResponseDto>> getWaitingOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getWaitingOrders(storeId, authentication.getName()));
    }

    @GetMapping("/preparing")
    public ResponseEntity<List<GetPreparingOrderResponseDto>> getPreparingOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getPreparingOrders(storeId, authentication.getName()));
    }

    @GetMapping("/complete")
    public ResponseEntity<List<GetCompleteOrderResponseDto>> getCompleteOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getCompleteOrders(storeId, authentication.getName()));
    }

    @PatchMapping("/{orderId}/accept")
    public ResponseEntity<Void> acceptOrders(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeOrderToPreparing(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<Void> readyOrders(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeOrderToReady(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeOrderToCancel(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }
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