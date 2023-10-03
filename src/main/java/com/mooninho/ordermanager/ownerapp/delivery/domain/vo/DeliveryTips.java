package com.mooninho.ordermanager.ownerapp.delivery.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DeliveryTips {

    private final String deliveryTips;

    private DeliveryTips(String deliveryTips) {
        this.deliveryTips = deliveryTips;
    }

    public static DeliveryTips of(Integer deliveryTips) {

        if (deliveryTips == null) {
            throw new IllegalArgumentException("배달팁을 입력하세요"); // TODO 배달비 정책 추가
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

        return new DeliveryTips(numberFormat.format(deliveryTips));
    }
}
