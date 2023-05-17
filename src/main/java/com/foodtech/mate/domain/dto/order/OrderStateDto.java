package com.foodtech.mate.domain.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderStateDto {

    private Long orderId;
    private String orderState;
}
