package com.order.manager.dto.delivery;

import com.order.manager.domain.entity.Delivery;
import com.order.manager.domain.entity.DeliveryCompany;
import com.order.manager.domain.entity.Order;
import com.order.manager.domain.wrapper.delivery.DeliveryTips;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private Integer deliveryTips;

    public static Delivery toEntity(
            Long orderId,
            Long deliveryCompanyId,
            Integer deliveryTips
    ) {
        return Delivery.createDeliveryInfo(
                Order.createKeyValue(orderId),
                DeliveryCompany.createKeyValue(deliveryCompanyId),
                DeliveryTips.of(deliveryTips)
        );
    }
}
