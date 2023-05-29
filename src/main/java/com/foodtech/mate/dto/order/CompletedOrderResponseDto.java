package com.foodtech.mate.dto.order;

import com.foodtech.mate.enums.state.OrderState;
import com.foodtech.mate.enums.type.OrderType;
import com.foodtech.mate.enums.type.PaymentType;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedOrderResponseDto {

    private LocalDateTime orderTimeStamp;
    private OrderType orderType;
    private PaymentType paymentType;
    private String menuName;
    private TotalPrice totalPrice;
    private String customerInfo;
    private OrderState orderState;
}
