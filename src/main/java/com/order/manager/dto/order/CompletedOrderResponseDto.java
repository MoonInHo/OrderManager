package com.order.manager.dto.order;

import com.order.manager.enums.state.OrderState;
import com.order.manager.enums.type.OrderType;
import com.order.manager.enums.type.PaymentType;
import com.order.manager.domain.wrapper.order.TotalPrice;
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
