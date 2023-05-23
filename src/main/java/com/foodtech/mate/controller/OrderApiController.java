package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.order.CompletedOrderResponseDto;
import com.foodtech.mate.domain.dto.order.OrderStateRequestDto;
import com.foodtech.mate.domain.dto.order.PreparingOrderResponseDto;
import com.foodtech.mate.domain.dto.order.WaitingOrderResponseDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    //TODO 요청 병합 여부 고민
    @GetMapping("/orders/waiting")
    public List<WaitingOrderResponseDto> waitingOrdersLookup() {
        return orderService.findWaitingOrders();
    }

    @GetMapping("/orders/preparing")
    public List<PreparingOrderResponseDto> preparingOrdersLookup() {
        return orderService.findPreparingOrders();
    }

    @GetMapping("/orders/complete")
    public List<CompletedOrderResponseDto> completedOrdersLookup() {
        return orderService.completeOrdersLookup();
    }

    @PatchMapping("/orders-accept")
    public ResponseEntity<String> acceptOrders(@RequestBody OrderStateRequestDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (isWaiting(orderState)) {
            orderService.changeOrderState(orderId, OrderState.PREPARING);
            return ResponseEntity.ok("주문이 수락되었습니다");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    @PatchMapping("/orders-ready")
    public ResponseEntity<String> readyOrders(@RequestBody OrderStateRequestDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (isPreparing(orderState)) {
            orderService.changeOrderState(orderId, OrderState.READY);
            return ResponseEntity.ok("메뉴가 준비되었습니다.");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    @PatchMapping("/orders-cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody OrderStateRequestDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (isNotCancel(orderState)) {
            orderService.changeOrderState(orderId, OrderState.CANCEL);
            return ResponseEntity.ok("주문이 취소되었습니다");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    @PatchMapping("/orders-pickup")
    public ResponseEntity<String> pickUpComplete(@RequestBody OrderStateRequestDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (isReady(orderState)) {
            orderService.changeOrderStateToPickUp(orderId, OrderState.COMPLETE);
            return ResponseEntity.ok("픽업이 완료되었습니다");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    private boolean isWaiting(OrderState orderState) {
        return orderState.equals(OrderState.WAITING);
    }
    private boolean isPreparing(OrderState orderState) {
        return orderState.equals(OrderState.PREPARING);
    }

    private boolean isNotCancel(OrderState orderState) {
        return orderState.equals(OrderState.CANCEL);
    }

    private boolean isReady(OrderState orderState) {
        return orderState.equals(OrderState.READY);
    }
}