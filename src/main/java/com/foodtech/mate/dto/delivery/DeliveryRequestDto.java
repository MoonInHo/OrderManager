package com.foodtech.mate.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequestDto {

    private Long deliveryDriverId;
    private String companyName;
    private Integer deliveryTips;
}
