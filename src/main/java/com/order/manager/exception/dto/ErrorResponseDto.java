package com.order.manager.exception.dto;

import com.order.manager.exception.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private ErrorCode code;
    private String message;
}