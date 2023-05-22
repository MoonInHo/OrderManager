package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.state.PaymentType;
import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingOrderDto {

    private Long orderId;
    private LocalDateTime orderTimestamp;
    private String menuNames;
    private TotalPrice totalPrice;
    private String customerInfo;
    private CustomerRequest customerRequest;
    private OrderState orderState;
    private OrderType orderType;
    private PaymentType paymentType;
}
