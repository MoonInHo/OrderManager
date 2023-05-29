package com.foodtech.mate.enums.state;

public enum DeliveryState {

    WAITING("배차대기"),
    DISPATCH("배차"),
    PICKUP("배달중"),
    COMPLETE("배달완료");

    private final String deliveryStateCode;

    DeliveryState(String deliveryStateCode) {
        this.deliveryStateCode = deliveryStateCode;
    }
}
