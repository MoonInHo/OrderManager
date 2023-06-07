package com.order.manager.exception.exception.delivery;

public class EmptyDeliveryListException extends RuntimeException {

    public EmptyDeliveryListException() {
        super("배달 목록이 비어있습니다.");
    }
}
