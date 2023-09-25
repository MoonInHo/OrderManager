package com.mooninho.ordermanager.임시.dto.delivery;

import com.mooninho.ordermanager.임시.domain.entity.Delivery;
import com.mooninho.ordermanager.임시.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.order.domain.entity.Order;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DeliveryTips;
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
                Order.createKeyObject(orderId),
                DeliveryCompany.createKeyValue(deliveryCompanyId),
                DeliveryTips.of(deliveryTips)
        );
    }
}
