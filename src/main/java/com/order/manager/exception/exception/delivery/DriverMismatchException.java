package com.order.manager.exception.exception.delivery;

public class DriverMismatchException extends RuntimeException {

    public DriverMismatchException() {
        super("라이더 정보가 일치하지 않습니다.");
    }
}
