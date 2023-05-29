package com.foodtech.mate.exception.exception.order;

public class InvalidOrderTypeException extends RuntimeException {

    public InvalidOrderTypeException() {
        super("주문 형태를 확인해주세요.");
    }
}
