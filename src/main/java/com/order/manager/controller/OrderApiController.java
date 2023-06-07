package com.order.manager.controller;

import com.order.manager.dto.delivery.DeliveryDetailResponseDto;
import com.order.manager.dto.delivery.DeliveryRequestDto;
import com.order.manager.dto.delivery.DeliveryTrackingResponseDto;
import com.order.manager.dto.order.CompletedOrderResponseDto;
import com.order.manager.dto.order.PreparingOrderResponseDto;
import com.order.manager.dto.order.WaitingOrderResponseDto;
import com.order.manager.enums.state.OrderState;
import com.order.manager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/waiting")
    public List<WaitingOrderResponseDto> waitingOrdersLookup() {
        return orderService.findWaitingOrders();
    }

    @GetMapping("/preparing")
    public List<PreparingOrderResponseDto> preparingOrdersLookup() {
        return orderService.findPreparingOrders();
    }

    @GetMapping("/complete")
    public List<CompletedOrderResponseDto> completedOrdersLookup() {
        return orderService.completeOrdersLookup();
    }

    @PatchMapping("/{orderId}/accept")
    public ResponseEntity<String> acceptOrders(@PathVariable Long orderId) {

        OrderState orderState = orderService.findOrderState(orderId);
        if (isWaiting(orderState)) {
            orderService.changeOrderState(orderId, OrderState.PREPARING);
            return ResponseEntity.ok("주문이 수락되었습니다");
        }
        return ResponseEntity.badRequest().body("주문이 대기중 상태가 아닙니다.");
    }

    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<String> readyOrders(@PathVariable Long orderId) {

        OrderState orderState = orderService.findOrderState(orderId);
        if (isPreparing(orderState)) {
            orderService.changeOrderState(orderId, OrderState.READY);
            return ResponseEntity.ok("메뉴가 준비되었습니다.");
        }
        return ResponseEntity.badRequest().body("주문이 준비중 상태가 아닙니다.");
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {

        OrderState orderState = orderService.findOrderState(orderId);
        if (isComplete(orderState)) {
            return ResponseEntity.badRequest().body("완료된 주문은 취소할 수 없습니다.");
        }
        if (isCancel(orderState)) {
            return ResponseEntity.badRequest().body("이미 취소된 주문입니다.");
        }

        orderService.changeOrderState(orderId, OrderState.CANCEL);
        return ResponseEntity.ok("주문이 취소되었습니다");
    }

    @PatchMapping("/{orderId}/pickup")
    public ResponseEntity<String> pickUpComplete(@PathVariable Long orderId) {

        OrderState orderState = orderService.findOrderState(orderId);
        if (isReady(orderState)) {
            orderService.changeOrderStateToPickUp(orderId, OrderState.COMPLETE);
            return ResponseEntity.ok("픽업이 완료되었습니다");
        }
        return ResponseEntity.badRequest().body("준비 완료된 포장 주문만 픽업할 수 있습니다.");
    }

    @PostMapping("/deliveries/{orderId}/create")
    public ResponseEntity<String> createDeliveryInfo(@PathVariable Long orderId, @RequestBody DeliveryRequestDto requestDeliveryDto) {

        orderService.createDeliveryInfo(orderId, requestDeliveryDto);

        return ResponseEntity.ok("배달원 배정을 요청했습니다");
    }

    @GetMapping("/deliveries")
    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery() {
        return orderService.lookupInProgressDelivery();
    }

    @GetMapping("/deliveries/{deliveryId}/detail")
    public List<DeliveryDetailResponseDto> deliveryDetailInfoLookup(@PathVariable Long deliveryId) {
        return orderService.deliveryDetailLookup(deliveryId);
    }

    private boolean isWaiting(OrderState orderState) {
        return orderState.equals(OrderState.WAITING);
    }

    private boolean isPreparing(OrderState orderState) {
        return orderState.equals(OrderState.PREPARING);
    }

    private boolean isComplete(OrderState orderState) {
        return orderState.equals(OrderState.COMPLETE);
    }

    private boolean isCancel(OrderState orderState) {
        return orderState.equals(OrderState.CANCEL);
    }

    private boolean isReady(OrderState orderState) {
        return orderState.equals(OrderState.READY);
    }
}