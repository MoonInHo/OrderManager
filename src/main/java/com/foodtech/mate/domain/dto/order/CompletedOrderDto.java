package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.state.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedOrderDto {

    private LocalDateTime orderTimeStamp;
    private OrderType orderType;
    private PaymentType paymentType;
    private String menuName;
    private String customerInfo;
    private OrderState orderState;
}
