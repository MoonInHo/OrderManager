package com.mooninho.ordermanager.임시.dto.order;

import com.mooninho.ordermanager.임시.enums.state.OrderState;
import com.mooninho.ordermanager.임시.enums.type.OrderType;
import com.mooninho.ordermanager.임시.enums.type.PaymentType;
import com.mooninho.ordermanager.임시.domain.wrapper.order.TotalPrice;
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
