package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetInProgressDeliveryOrdersResponseDto {

    private Long id;
    private String timestamp;
    private OrderType orderType;
    private String companyName;
    private PaymentType paymentType;
    private String totalPrice;
    private DeliveryStatus deliveryStatus;
}
