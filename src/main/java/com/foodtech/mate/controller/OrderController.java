package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.order.OrderStateDto;
import com.foodtech.mate.domain.dto.order.PendingOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.service.OrderService;
import com.foodtech.mate.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.foodtech.mate.controller.verifier.ProfileProcessing.fetchLoggedInUserKey;
import static com.foodtech.mate.domain.state.OrderState.findByOrderState;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StoreService storeService;

    @PostMapping("/view-pending-order")
    public List<PendingOrderDto> viewPendingOrder() {

        Long storeId = storeService.findStoreId(fetchLoggedInUserKey());

        return orderService.findPendingOrder(storeId);
    }

    @PutMapping("/change-order-state")
    public void changeOrderState(@RequestBody OrderStateDto orderStateDto) {

        Long orderId = orderStateDto.getOrderId();
        String inputOrderState = orderStateDto.getOrderState();

        OrderState orderState = orderService.checkOrderState(orderId);
        if (!orderState.equals(OrderState.WAITING)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }

        OrderState orderStateCode = findByOrderState(inputOrderState);
        if (orderStateCode == null) {
            throw new IllegalArgumentException("사용할 수 없는 주문 상태 코드입니다");
        }
        orderService.changeOrderState(orderId, orderStateCode);
    }
}