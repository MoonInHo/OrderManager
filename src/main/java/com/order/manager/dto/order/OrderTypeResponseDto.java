package com.order.manager.dto.order;

import com.order.manager.enums.state.OrderState;
import com.order.manager.enums.type.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTypeResponseDto {

    private OrderType orderType;
    private OrderState orderState;
}
