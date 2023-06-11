package com.order.manager.enums.state;

public enum OrderState {

    WAITING("대기중"),
    PREPARING("준비중"),
    READY("준비완료"),
    COMPLETE("완료"),
    CANCEL("취소");

    private String orderStateCode;

    OrderState(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }
}
