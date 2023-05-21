package com.foodtech.mate.exception.dto;

import com.foodtech.mate.exception.code.OrderErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderErrorResponseDto {

    private OrderErrorCode code;
    private String massage;
}
