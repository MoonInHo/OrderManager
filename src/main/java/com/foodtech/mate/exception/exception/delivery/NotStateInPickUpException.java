package com.foodtech.mate.exception.exception.delivery;

public class NotStateInPickUpException extends RuntimeException {

    public NotStateInPickUpException() {
        super("픽업 상태에서만 가능한 요청입니다.");
    }
}
