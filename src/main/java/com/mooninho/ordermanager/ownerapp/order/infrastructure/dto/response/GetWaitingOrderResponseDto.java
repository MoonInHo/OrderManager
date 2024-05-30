package com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response;

import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetWaitingOrderResponseDto {

    private Long id;
    private String timestamp;
    private String totalPrice;
    private List<String> menuNames;
    private String address;
    private String contact;
    private String customerRequest;
    private OrderType orderType;
    private PaymentType paymentType;
}
