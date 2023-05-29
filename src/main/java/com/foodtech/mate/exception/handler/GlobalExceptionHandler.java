package com.foodtech.mate.exception.handler;

import com.foodtech.mate.exception.code.ErrorCode;
import com.foodtech.mate.exception.dto.ErrorResponseDto;
import com.foodtech.mate.exception.exception.EmptyResultDataAccessException;
import com.foodtech.mate.exception.exception.NotFoundStateCodeException;
import com.foodtech.mate.exception.exception.delivery.*;
import com.foodtech.mate.exception.exception.member.MemberNotFoundException;
import com.foodtech.mate.exception.exception.member.PhoneExistException;
import com.foodtech.mate.exception.exception.member.SamePasswordException;
import com.foodtech.mate.exception.exception.member.UserIdExistException;
import com.foodtech.mate.exception.exception.order.EmptyOrderListException;
import com.foodtech.mate.exception.exception.order.InvalidOrderTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIdExistException.class)
    public ErrorResponseDto duplicateCheckException(UserIdExistException e) {
        return new ErrorResponseDto(ErrorCode.USERID_EXIST_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhoneExistException.class)
    public ErrorResponseDto phoneExistException(PhoneExistException e) {
        return new ErrorResponseDto(ErrorCode.PHONE_EXIST_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberNotFoundException.class)
    public ErrorResponseDto memberNotFoundException(MemberNotFoundException e) {
        return new ErrorResponseDto(ErrorCode.ENTITY_NOT_FOUND_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SamePasswordException.class)
    public ErrorResponseDto samePasswordException(SamePasswordException e) {
        return new ErrorResponseDto(ErrorCode.SAME_PASSWORD_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyOrderListException.class)
    public ErrorResponseDto emptyOrderListException(EmptyOrderListException e) {
        return new ErrorResponseDto(ErrorCode.EMPTY_ORDER_LIST_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorResponseDto emptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ErrorResponseDto(ErrorCode.EMPTY_RESULT_DATE_ACCESS_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOrderTypeException.class)
    public ErrorResponseDto invalidOrderTypeException(InvalidOrderTypeException e) {
        return new ErrorResponseDto(ErrorCode.INVALID_ORDER_TYPE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotDeliveryException.class)
    public ErrorResponseDto notDeliveryException(NotDeliveryException e) {
        return new ErrorResponseDto(ErrorCode.NOT_DELIVERY_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotReadyException.class)
    public ErrorResponseDto notReadyException(NotReadyException e) {
        return new ErrorResponseDto(ErrorCode.NOT_READY_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotWaitingException.class)
    public ErrorResponseDto notDeliveryException(NotWaitingException e) {
        return new ErrorResponseDto(ErrorCode.NOT_WAITING_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DriverMismatchException.class)
    public ErrorResponseDto driverMismatchException(DriverMismatchException e) {
        return new ErrorResponseDto(ErrorCode.DRIVER_MISMATCH_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotStateInDispatchException.class)
    public ErrorResponseDto notStateInDispatchException(NotStateInDispatchException e) {
        return new ErrorResponseDto(ErrorCode.NOT_STATE_IN_DISPATCH_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotStateInPickUpException.class)
    public ErrorResponseDto notStateInPickUpException(NotStateInPickUpException e) {
        return new ErrorResponseDto(ErrorCode.NOT_STATE_IN_PICKUP_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundStateCodeException.class)
    public ErrorResponseDto notFoundStateCodeException(NotFoundStateCodeException e) {
        return new ErrorResponseDto(ErrorCode.NOT_FOUND_STATE_CODE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyDeliveryListException.class)
    public ErrorResponseDto emptyDeliveryListException(EmptyDeliveryListException e) {
        return new ErrorResponseDto(ErrorCode.EMPTY_DELIVERY_LIST_ERROR, e.getMessage());
    }
}