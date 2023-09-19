package com.mooninho.ordermanager.임시.enums.type;

public enum OrderType {

    //TODO 테이블 주문 기능 추가
    DELIVERY("배달"),
    TOGO("포장"),
    TABLE("테이블");

    private String orderTypeCode;

    OrderType(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }
}
