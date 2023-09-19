package com.mooninho.ordermanager.임시.dto.delivery;

import com.mooninho.ordermanager.임시.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDto {

    private Long orderId;
    private DeliveryState deliveryState;
    private DeliveryCompany deliveryCompany;
    private Long deliveryDriverId;
}
