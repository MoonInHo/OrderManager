package com.order.manager.exception.exception;

public class ChangeOrderStatusException extends RuntimeException {

    public ChangeOrderStatusException() {
        super("주문 상태를 변경할 수 없습니다.");
    }
}
