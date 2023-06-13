package com.order.manager.exception.exception.delivery;

public class NotChangedDeliveryStateException extends RuntimeException {

    public NotChangedDeliveryStateException() {
        super("배달 상태를 변경할 수 없습니다.");
    }
}
