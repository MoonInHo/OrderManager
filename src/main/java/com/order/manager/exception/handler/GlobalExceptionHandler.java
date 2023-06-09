package com.order.manager.exception.handler;

import com.order.manager.exception.code.ErrorCode;
import com.order.manager.exception.dto.ErrorResponseDto;
import com.order.manager.exception.exception.*;
import com.order.manager.exception.exception.delivery.*;
import com.order.manager.exception.exception.member.*;
import com.order.manager.exception.exception.order.EmptyOrderListException;
import com.order.manager.exception.exception.order.InvalidOrderTypeException;
import com.order.manager.exception.exception.verification.VerificationBlockedException;
import com.order.manager.exception.exception.verification.VerificationCodeNotFoundException;
import com.order.manager.exception.exception.verification.VerificationFailureException;
import com.order.manager.exception.exception.verification.VerificationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyValueException.class)
    public ErrorResponseDto emptyValueException(EmptyValueException e) {
        return new ErrorResponseDto(ErrorCode.EMPTY_VALUE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullValueException.class)
    public ErrorResponseDto nullValueException(NullValueException e) {
        return new ErrorResponseDto(ErrorCode.NULL_VALUE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ErrorResponseDto invalidFormatException(InvalidFormatException e) {
        return new ErrorResponseDto(ErrorCode.INVALID_FORMAT_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIdExistException.class)
    public ErrorResponseDto userIdExistException(UserIdExistException e) {
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
        return new ErrorResponseDto(ErrorCode.ACCOUNT_NOT_FOUND_ERROR, e.getMessage());
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
    @ExceptionHandler(NotChangedOrderStateException.class)
    public ErrorResponseDto changeOrderStatusException(NotChangedOrderStateException e) {
        return new ErrorResponseDto(ErrorCode.CHANGE_ORDER_STATE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOrderStateException.class)
    public ErrorResponseDto notDeliveryException(InvalidOrderStateException e) {
        return new ErrorResponseDto(ErrorCode.INVALID_ORDER_STATE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOrderTypeException.class)
    public ErrorResponseDto invalidOrderTypeException(InvalidOrderTypeException e) {
        return new ErrorResponseDto(ErrorCode.INVALID_ORDER_TYPE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotDeliveryException.class)
    public ErrorResponseDto notDeliveryException(NotDeliveryException e) {
        return new ErrorResponseDto(ErrorCode.NOT_STATE_IN_DELIVERY_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotReadyException.class)
    public ErrorResponseDto notReadyException(NotReadyException e) {
        return new ErrorResponseDto(ErrorCode.NOT_STATE_IN_READY_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DriverMismatchException.class)
    public ErrorResponseDto driverMismatchException(DriverMismatchException e) {
        return new ErrorResponseDto(ErrorCode.DRIVER_MISMATCH_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncompleteDeliveryException.class)
    public ErrorResponseDto incompleteDeliveryException(IncompleteDeliveryException e) {
        return new ErrorResponseDto(ErrorCode.INCOMPLETE_DELIVERY_ERROR, e.getMessage());
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
    @ExceptionHandler(EmptyDeliveryException.class)
    public ErrorResponseDto emptyDeliveryException(EmptyDeliveryException e) {
        return new ErrorResponseDto(ErrorCode.EMPTY_DELIVERY_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyDeliveryListException.class)
    public ErrorResponseDto emptyDeliveryListException(EmptyDeliveryListException e) {
        return new ErrorResponseDto(ErrorCode.EMPTY_DELIVERY_LIST_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompanyMismatchException.class)
    public ErrorResponseDto companyMismatchException(CompanyMismatchException e) {
        return new ErrorResponseDto(ErrorCode.COMPANY_MISMATCH_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDeliveryStateException.class)
    public ErrorResponseDto invalidDeliveryStateException(InvalidDeliveryStateException e) {
        return new ErrorResponseDto(ErrorCode.INVALID_DELIVERY_STATE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotChangedDeliveryStateException.class)
    public ErrorResponseDto notChangedDeliveryStateException(NotChangedDeliveryStateException e) {
        return new ErrorResponseDto(ErrorCode.INVALID_DELIVERY_STATE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationCodeNotFoundException.class)
    public ErrorResponseDto verificationCodeNotFoundException(VerificationCodeNotFoundException e) {
        return new ErrorResponseDto(ErrorCode.VERIFICATION_CODE_NOT_FOUND_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationBlockedException.class)
    public ErrorResponseDto verificationBlockedException(VerificationBlockedException e) {
        return new ErrorResponseDto(ErrorCode.VERIFICATION_BLOCKED_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationFailureException.class)
    public ErrorResponseDto verificationFailureException(VerificationFailureException e) {
        return new ErrorResponseDto(ErrorCode.VERIFICATION_FAILURE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationNotFoundException.class)
    public ErrorResponseDto verificationNotFoundException(VerificationNotFoundException e) {
        return new ErrorResponseDto(ErrorCode.VERIFICATION_NOT_FOUND_ERROR, e.getMessage());
    }
}