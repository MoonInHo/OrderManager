package com.foodtech.mate.exception.dto;

import com.foodtech.mate.exception.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private ErrorCode code;
    private String message;
}