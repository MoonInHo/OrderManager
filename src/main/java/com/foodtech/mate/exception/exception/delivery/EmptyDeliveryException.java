package com.foodtech.mate.exception.exception.delivery;

public class EmptyDeliveryException extends RuntimeException {

    public EmptyDeliveryException() {
        super("존재하지 않는 배달입니다.");
    }
}
