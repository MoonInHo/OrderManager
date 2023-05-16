package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.order.PendingOrderDto;
import com.foodtech.mate.service.OrderService;
import com.foodtech.mate.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.foodtech.mate.controller.verifier.fetch.fetchLoggedInUserKey;

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
}