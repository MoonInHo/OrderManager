package com.order.manager.controller;

import com.order.manager.dto.ResponseMessageDto;
import com.order.manager.dto.delivery.DeliveryDetailResponseDto;
import com.order.manager.dto.delivery.DeliveryRequestDto;
import com.order.manager.dto.delivery.DeliveryTrackingResponseDto;
import com.order.manager.dto.order.CompletedOrderResponseDto;
import com.order.manager.dto.order.PreparingOrderResponseDto;
import com.order.manager.dto.order.WaitingOrderResponseDto;
import com.order.manager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/waiting")
    public List<WaitingOrderResponseDto> waitingOrdersLookup() {

        Long storeId = orderService.findStoreId();

        return orderService.findWaitingOrders(storeId);
    }

    @GetMapping("/preparing")
    public List<PreparingOrderResponseDto> preparingOrdersLookup() {

        Long storeId = orderService.findStoreId();

        return orderService.findPreparingOrders(storeId);
    }

    @GetMapping("/complete")
    public List<CompletedOrderResponseDto> completedOrdersLookup() {

        Long storeId = orderService.findStoreId();

        return orderService.completeOrdersLookup(storeId);
    }

    @PatchMapping("/{orderId}/accept")
    public ResponseEntity<ResponseMessageDto> acceptOrders(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        orderService.changeOrderStateToPreparing(storeId, orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("주문이 수락되었습니다"));
    }

    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<ResponseMessageDto> readyOrders(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        orderService.changeOrderStateToReady(storeId, orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("메뉴가 준비되었습니다."));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<ResponseMessageDto> cancelOrder(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        orderService.changeOrderStateToCancel(storeId, orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("주문이 취소되었습니다"));
    }

    @PatchMapping("/deliveries/{orderId}/complete")
    public ResponseEntity<ResponseMessageDto> completeDeliveryOrder(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        orderService.changeOrderStateToComplete(storeId, orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("배달주문 처리가 완료되었습니다."));
    }

    @PatchMapping("/{orderId}/pickup")
    public ResponseEntity<ResponseMessageDto> pickUpComplete(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        orderService.toPickup(storeId, orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("픽업이 완료되었습니다."));
    }

    @PostMapping("/{orderId}/deliveries")
    public ResponseEntity<ResponseMessageDto> createDeliveryRequest(@PathVariable Long orderId, @RequestBody DeliveryRequestDto DeliveryRequestDto) {

        Long storeId = orderService.findStoreId();

        orderService.createDeliveryInfo(orderId, storeId, DeliveryRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("배달원 배정을 요청했습니다"));
    }

    @GetMapping("/deliveries")
    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery() {

        Long storeId = orderService.findStoreId();

        return orderService.lookupInProgressDelivery(storeId);
    }

    @GetMapping("/deliveries/{deliveryId}/detail")
    public List<DeliveryDetailResponseDto> deliveryDetailInfoLookup(@PathVariable Long deliveryId) {

        Long storeId = orderService.findStoreId();

        return orderService.deliveryDetailLookup(storeId, deliveryId);
    }
}