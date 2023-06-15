package com.order.manager.exception.exception;

public class IncompleteDeliveryException extends RuntimeException {

    public IncompleteDeliveryException() {
        super("배달완료 상태에서만 가능한 요청입니다.");
    }
}
