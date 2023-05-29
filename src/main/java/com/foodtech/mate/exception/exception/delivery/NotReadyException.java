package com.foodtech.mate.exception.exception.delivery;

public class NotReadyException extends RuntimeException {

    public NotReadyException() {
        super("준비완료 상태에서만 가능한 요청입니다.");
    }
}
