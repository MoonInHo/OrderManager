package com.mooninho.ordermanager.order.infrastructure.dto.response;

import com.mooninho.ordermanager.order.domain.enums.OrderType;
import com.mooninho.ordermanager.order.domain.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetCompleteOrderResponseDto {

    private Long id;
    private String orderTimeStamp;
//    private List<String> menuNames;
    private String totalPrice;
    private String address;
    private OrderType orderType;
    private PaymentType paymentType;
}
