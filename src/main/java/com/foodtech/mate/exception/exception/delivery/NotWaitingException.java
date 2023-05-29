package com.foodtech.mate.exception.exception.delivery;

public class NotWaitingException extends RuntimeException {

    public NotWaitingException() {
        super("준비완료 상태에서만 가능한 요청입니다.");
    }
}
