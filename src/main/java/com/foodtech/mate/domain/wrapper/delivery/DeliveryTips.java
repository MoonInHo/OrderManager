package com.foodtech.mate.domain.wrapper.delivery;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DeliveryTips {

    private final Integer deliveryTips;

    private DeliveryTips(Integer deliveryTips) {
        this.deliveryTips = deliveryTips;
    }

    public static DeliveryTips of(Integer deliveryTips) {

        if (deliveryTips == null) {
            throw new IllegalArgumentException("배달팁을 입력하세요");
        }
        return new DeliveryTips(deliveryTips);
    }
}
