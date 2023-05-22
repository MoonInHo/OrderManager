package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.order.CompletedOrderDto;
import com.foodtech.mate.domain.dto.order.OrderStateDto;
import com.foodtech.mate.domain.dto.order.PreparingOrderDto;
import com.foodtech.mate.domain.dto.order.WaitingOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/waiting-orders-lookup")
    public List<WaitingOrderDto> waitingOrdersLookup() {
        return orderService.findWaitingOrders();
    }

    @PostMapping("/preparing-orders-lookup")
    public List<PreparingOrderDto> preparingOrdersLookup() {
        return orderService.findPreparingOrders();
    }

    @PostMapping("/completed-orders-lookup")
    public List<CompletedOrderDto> completedOrdersLookup() {
        return orderService.completeOrdersLookup();
    }

    @PutMapping("/accept-order")
    public ResponseEntity<String> acceptOrder(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();
        //TODO 메소드 추출 여부 판단하기 (acceptOrder, readyOrder, cancelOrder)
        OrderState orderState = orderService.findOrderState(orderId);
        if (orderState.equals(OrderState.WAITING)) {
            orderService.changeOrderState(orderId, OrderState.PREPARING);
            return ResponseEntity.ok("주문이 수락되었습니다");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    @PutMapping("/ready-order")
    public ResponseEntity<String> readyOrder(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (orderState.equals(OrderState.PREPARING)) {
            orderService.changeOrderState(orderId, OrderState.READY);
            return ResponseEntity.ok("메뉴가 준비되었습니다.");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    @PutMapping("/cancel-order")
    public ResponseEntity<String> cancelOrder(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (!orderState.equals(OrderState.CANCEL)) {
            orderService.changeOrderState(orderId, OrderState.CANCEL);
            return ResponseEntity.ok("주문이 취소되었습니다");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }

    @PutMapping("/pickup-complete")
    public ResponseEntity<String> pickUpComplete(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();

        OrderState orderState = orderService.findOrderState(orderId);
        if (orderState.equals(OrderState.READY)) {
            orderService.changeOrderStateToPickUp(orderId, OrderState.COMPLETE);
            return ResponseEntity.ok("픽업이 완료되었습니다");
        }
        return ResponseEntity.badRequest().body("올바르지 않은 입력입니다");
    }
}