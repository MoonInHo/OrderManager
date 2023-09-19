package com.mooninho.ordermanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private ErrorCode errorCode;
    private String message;
}
