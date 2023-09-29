package com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response;

import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPreparingOrderResponseDto {

    private Long id;
    private String timestamp;
//    private List<String> menuNames;
    private String totalPrice;
    private String address;
    private String addressDetail;
    private String contact;
    private String customerRequest;
    private OrderType orderType;
    private PaymentType paymentType;

}
