package com.mooninho.ordermanager.order.infrastructure.dto.response;

import com.mooninho.ordermanager.order.domain.enums.OrderType;
import com.mooninho.ordermanager.order.domain.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
