package com.foodtech.mate.exception.exception.delivery;

public class NotStateInDispatchException extends RuntimeException {

    public NotStateInDispatchException() {
        super("배차 상태에서만 가능한 요청입니다.");
    }
}
