package com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetDeliveryOrderResponseDto {

    private Long id;
    private DeliveryStatus deliveryStatus;
    private String companyName;
    private String deliveryTips;
    private String driverName;
    private String driverPhone;
    private String contact;
    private String address;
    private String addressDetail;
    private String timestamp;
    private OrderType orderType;
}
