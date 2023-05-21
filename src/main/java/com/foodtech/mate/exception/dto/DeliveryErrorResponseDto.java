package com.foodtech.mate.exception.dto;

import com.foodtech.mate.exception.code.DeliveryErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliveryErrorResponseDto {

    private DeliveryErrorCode code;
    private String message;
}
