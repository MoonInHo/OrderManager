package com.foodtech.mate.domain.state;

import java.util.Arrays;

public enum OrderType {

    //TODO 테이블 주문 기능 추가
    DELIVERY("배달"),
    TOGO("포장"),
    TABLE("테이블");

    private String orderTypeCode;

    OrderType(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public static OrderType findByOrderType(String orderTypeCode){

        return Arrays.stream(OrderType.values())
                .filter(orderType -> orderType.orderTypeCode.equals(orderTypeCode))
                .findAny()
                .orElse(null);
    }
}
