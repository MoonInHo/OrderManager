package com.order.manager.exception.exception;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException() {
        super("! 올바른 형식으로 입력해주세요.");
    }
}
