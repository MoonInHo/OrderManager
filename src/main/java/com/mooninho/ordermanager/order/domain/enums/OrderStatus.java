package com.mooninho.ordermanager.order.domain.enums;

public enum OrderStatus {

    WAITING("대기중"),
    PREPARING("준비중"),
    READY("준비완료"),
    COMPLETE("완료"),
    CANCEL("취소");

    private String orderStateCode;

    OrderStatus(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }
}
