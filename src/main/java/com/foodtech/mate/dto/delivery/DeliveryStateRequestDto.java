package com.foodtech.mate.dto.delivery;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryStateRequestDto {

    private Long deliveryId;
    private String deliveryState;
}
