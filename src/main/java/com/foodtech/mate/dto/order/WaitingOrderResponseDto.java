package com.foodtech.mate.dto.order;

import com.foodtech.mate.enums.state.OrderState;
import com.foodtech.mate.enums.type.OrderType;
import com.foodtech.mate.enums.type.PaymentType;
import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingOrderResponseDto {

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
