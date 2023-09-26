package com.mooninho.ordermanager.order.application.event;

import com.mooninho.ordermanager.order.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderService orderService;

    @Async
    @EventListener
    public void onOrderHasCanceledEvent(OrderHasCanceledEvent orderHasCanceledEvent) {
        orderService.createCancelHistory(
                orderHasCanceledEvent.getOrderId(),
                orderHasCanceledEvent.getCreateOrderCancelHistoryRequestDto()
        );
    }
}
