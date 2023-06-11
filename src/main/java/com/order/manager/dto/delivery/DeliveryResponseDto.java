package com.order.manager.dto.delivery;

import com.order.manager.domain.entity.DeliveryCompany;
import com.order.manager.enums.state.DeliveryState;
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
