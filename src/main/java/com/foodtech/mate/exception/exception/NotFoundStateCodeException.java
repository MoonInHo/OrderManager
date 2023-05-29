package com.foodtech.mate.exception.exception;

public class NotFoundStateCodeException extends RuntimeException {

    public NotFoundStateCodeException() {
        super("올바르지 않은 요청입니다.");
    }
}
