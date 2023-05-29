package com.foodtech.mate.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStateRequestDto {

    private Long orderId;
    private String orderState;
    private String orderType;
}
