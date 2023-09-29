package com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.delivery.domain.vo.Company;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetInProgressDeliveryOrdersResponseDto {

    private Long id;
    private String timestamp;
    private OrderType orderType;
    private Company company;
    private PaymentType paymentType;
    private String totalPrice;
    private DeliveryStatus deliveryStatus;
}
