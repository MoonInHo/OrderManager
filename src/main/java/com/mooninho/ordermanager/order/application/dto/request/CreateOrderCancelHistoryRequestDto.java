package com.mooninho.ordermanager.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderCancelHistoryRequestDto {

    private String cancelReason;
}
