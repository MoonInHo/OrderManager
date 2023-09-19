package com.mooninho.ordermanager.임시.dto.delivery;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryStateRequestDto {

    private Long deliveryId;
    private String deliveryState;
}
