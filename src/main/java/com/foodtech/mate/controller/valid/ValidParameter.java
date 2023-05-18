package com.foodtech.mate.controller.valid;

import com.foodtech.mate.domain.state.OrderState;

import static com.foodtech.mate.domain.state.OrderState.findByOrderState;

public class ValidParameter {

    public static OrderState validOrderState(String inputOrderState) {
        OrderState orderStateCode = findByOrderState(inputOrderState);
        if (orderStateCode == null) {
            throw new IllegalArgumentException("사용할 수 없는 주문 상태 코드입니다");
        }
        return orderStateCode;
    }

}
