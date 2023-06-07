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

    public static Delivery toEntity(Order order, DeliveryCompany deliveryCompany, Integer deliveryTips) {
        return Delivery.createDeliveryInfo(
                order,
                deliveryCompany,
                DeliveryTips.of(deliveryTips));
    }
}
