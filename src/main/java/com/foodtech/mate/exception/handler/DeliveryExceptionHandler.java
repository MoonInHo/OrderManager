package com.foodtech.mate.exception.handler;

import com.foodtech.mate.exception.code.DeliveryErrorCode;
import com.foodtech.mate.exception.dto.DeliveryErrorResponseDto;
import com.foodtech.mate.exception.exception.NoDeliveryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeliveryExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoDeliveryException.class)
    public DeliveryErrorResponseDto noDeliveryException(NoDeliveryException e) {
        return new DeliveryErrorResponseDto(DeliveryErrorCode.NO_DELIVERY_ERROR, e.getMessage());
    }
}
