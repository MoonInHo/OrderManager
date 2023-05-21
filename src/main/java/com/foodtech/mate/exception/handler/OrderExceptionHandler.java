package com.foodtech.mate.exception.handler;

import com.foodtech.mate.exception.code.OrderErrorCode;
import com.foodtech.mate.exception.dto.OrderErrorResponseDto;
import com.foodtech.mate.exception.exception.NoOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoOrderException.class)
    public OrderErrorResponseDto duplicateCheckException(NoOrderException e) {
        return new OrderErrorResponseDto(OrderErrorCode.NO_ORDER_ERROR, e.getMessage());
    }
}
