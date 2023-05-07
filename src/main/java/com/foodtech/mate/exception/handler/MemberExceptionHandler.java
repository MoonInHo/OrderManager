package com.foodtech.mate.exception.handler;

import com.foodtech.mate.exception.code.MemberErrorCode;
import com.foodtech.mate.exception.dto.ErrorResponseDto;
import com.foodtech.mate.exception.exception.InvalidAuthCodeException;
import com.foodtech.mate.exception.exception.MisMatchedPasswordException;
import com.foodtech.mate.exception.exception.VerificationFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponseDto duplicateCheckException(IllegalStateException e) {
        return new ErrorResponseDto(MemberErrorCode.DUPLICATE_CHECK_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationFailureException.class)
    public ErrorResponseDto verificationFailureException(VerificationFailureException e) {
        return new ErrorResponseDto(MemberErrorCode.VERIFICATION_FAILURE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MisMatchedPasswordException.class)
    public ErrorResponseDto mismatchedPasswordException(MisMatchedPasswordException e) {
        return new ErrorResponseDto(MemberErrorCode.MIS_MATCHED_PASSWORD_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAuthCodeException.class)
    public ErrorResponseDto InvalidAuthCodeException(InvalidAuthCodeException e) {
        return new ErrorResponseDto(MemberErrorCode.INVALID_AUTH_CODE_ERROR, e.getMessage());
    }
}