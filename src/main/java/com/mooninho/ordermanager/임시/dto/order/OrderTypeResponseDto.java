package com.mooninho.ordermanager.임시.dto.order;

import com.mooninho.ordermanager.임시.enums.state.OrderState;
import com.mooninho.ordermanager.임시.enums.type.OrderType;
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
