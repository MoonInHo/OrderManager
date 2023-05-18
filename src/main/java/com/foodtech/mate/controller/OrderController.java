package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.order.OrderStateDto;
import com.foodtech.mate.domain.dto.order.FindOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.service.OrderService;
import com.foodtech.mate.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.foodtech.mate.controller.valid.ValidParameter.validOrderState;
import static com.foodtech.mate.controller.verifier.ProfileProcessing.fetchLoggedInUserKey;
import static com.foodtech.mate.domain.state.OrderState.findByOrderState;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StoreService storeService;

    @PostMapping("/view-orders")
    public List<FindOrderDto> viewWaitingOrder(@RequestBody OrderStateDto orderStateDto) {

        String inputOrderState = orderStateDto.getOrderState();
        OrderState orderStateCode = findByOrderState(inputOrderState);

        Long storeId = storeService.findStoreId(fetchLoggedInUserKey());

        return orderService.findWaitingOrder(storeId, orderStateCode);
    }

    @PutMapping("/accept-order")
    public ResponseEntity<String> changeOrderState(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();
        String inputOrderState = orderStateDto.getOrderState();

        OrderState orderState = orderService.checkOrderState(orderId);
        if (!orderState.equals(OrderState.WAITING)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        OrderState orderStateCode = validOrderState(inputOrderState);

        orderService.changeOrderState(orderId, orderStateCode);

        return ResponseEntity.ok("주문이 수락되었습니다.");
    }

    @PutMapping("/cancel-order")
    public ResponseEntity<String> cancelOrder(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();
        String inputOrderState = orderStateDto.getOrderState();

        OrderState orderState = orderService.checkOrderState(orderId);
        if (orderState.equals(OrderState.CANCEL)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        OrderState orderStateCode = validOrderState(inputOrderState);

        orderService.changeOrderState(orderId, orderStateCode);

        return ResponseEntity.ok("주문이 취소되었습니다");
    }
}