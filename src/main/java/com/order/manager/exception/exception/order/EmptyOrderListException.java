package com.order.manager.exception.exception.order;

public class EmptyOrderListException extends RuntimeException {

    public EmptyOrderListException() {
        super("주문 목록이 비어있습니다.");
    }
}
