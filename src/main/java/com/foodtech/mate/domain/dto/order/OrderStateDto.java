package com.foodtech.mate.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStateDto {

    private Long orderId;
    private String orderState;
    private String orderType;
}
