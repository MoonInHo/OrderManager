package com.foodtech.mate.domain.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDeliveryDto {

    private Long orderId;
    private Long deliveryId;
    private Long deliveryDriverId;
    private Integer deliveryTips;
}
