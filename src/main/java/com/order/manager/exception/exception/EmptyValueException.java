package com.order.manager.exception.exception;

public class EmptyValueException extends RuntimeException {

    public EmptyValueException() {
        super("! 공백을 사용할 수 없습니다.");
    }
}
