package com.foodtech.mate.domain.dto.delivery;

import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.domain.entity.Order;
import com.foodtech.mate.domain.wrapper.delivery.DeliveryTips;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private Integer deliveryTips;

    public static Delivery toEntity(Order order, Integer deliveryTips) {
        return Delivery.createDeliveryInfo(order ,DeliveryTips.of(deliveryTips));
    }
}
