package com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response;

import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDetailResponseDto {

    private Long id;
    private String timestamp;
//    private List<OrderDetail> orderDetails;
    private String totalPrice;
    private String address;
    private String addressDetail;
    private String contact;
    private String customerRequest;
    private OrderType orderType;
    private PaymentType paymentType;
    private OrderStatus orderStatus;
}
