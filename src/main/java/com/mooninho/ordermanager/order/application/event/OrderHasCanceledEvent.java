package com.mooninho.ordermanager.order.application.event;

import com.mooninho.ordermanager.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderHasCanceledEvent {

    private Long orderId;
    private CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto;
}
