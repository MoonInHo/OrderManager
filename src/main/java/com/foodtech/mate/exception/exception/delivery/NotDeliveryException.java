package com.foodtech.mate.exception.exception.delivery;

public class NotDeliveryException extends RuntimeException {

    public NotDeliveryException() {
        super("배달 주문에서만 가능한 요청입니다.");
    }
}
