package com.foodtech.mate.domain.state;

import java.util.Arrays;

public enum DeliveryState {

    WAITING("배차대기"),
    DISPATCH("배차"),
    PICKUP("배달중"),
    COMPLETE("배달완료");

    private String deliveryStateCode;

    DeliveryState(String deliveryStateCode) {
        this.deliveryStateCode = deliveryStateCode;
    }

    public static DeliveryState findByDeliveryState(String deliveryStateCode){

        return Arrays.stream(DeliveryState.values())
                .filter(deliveryState -> deliveryState.deliveryStateCode.equals(deliveryStateCode))
                .findAny()
                .orElse(null);
    }
}
