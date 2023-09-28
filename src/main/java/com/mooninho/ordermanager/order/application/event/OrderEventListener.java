package com.mooninho.ordermanager.order.application.event;

import com.mooninho.ordermanager.orderhistory.application.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderHistoryService orderHistoryService;

    @Async
    @EventListener
    public void onOrderHasCanceledEvent(OrderHasCanceledEvent orderHasCanceledEvent) {
        orderHistoryService.createCancelHistory(
                orderHasCanceledEvent.getOrderId(),
                orderHasCanceledEvent.getCreateOrderCancelHistoryRequestDto()
        );
    }
}
