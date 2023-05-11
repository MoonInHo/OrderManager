package com.foodtech.mate.domain.wrapper.delivery;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DeliveryTips {

    private final String deliveryTips;

    private DeliveryTips(String deliveryTips) {
        this.deliveryTips = deliveryTips;
    }

    public static DeliveryTips of(Integer deliveryTips) {

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
        String formattedDeliveryTips = format.format(deliveryTips);

        return new DeliveryTips(formattedDeliveryTips);
    }
}
