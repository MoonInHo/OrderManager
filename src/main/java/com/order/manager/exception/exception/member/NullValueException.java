package com.order.manager.exception.exception.member;

public class NullValueException extends RuntimeException {

    public NullValueException() {
        super("! 필수 잆력값 입니다.");
    }
}
