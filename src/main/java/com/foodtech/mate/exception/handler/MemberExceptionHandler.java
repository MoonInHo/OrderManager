package com.foodtech.mate.exception.handler;

import com.foodtech.mate.exception.code.MemberErrorCode;
import com.foodtech.mate.exception.dto.MemberErrorResponseDto;
import com.foodtech.mate.exception.exception.DuplicateCheckException;
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
    @ExceptionHandler(DuplicateCheckException.class)
    public MemberErrorResponseDto duplicateCheckException(DuplicateCheckException e) {
        return new MemberErrorResponseDto(MemberErrorCode.DUPLICATE_CHECK_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationFailureException.class)
    public MemberErrorResponseDto verificationFailureException(VerificationFailureException e) {
        return new MemberErrorResponseDto(MemberErrorCode.VERIFICATION_FAILURE_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MisMatchedPasswordException.class)
    public MemberErrorResponseDto mismatchedPasswordException(MisMatchedPasswordException e) {
        return new MemberErrorResponseDto(MemberErrorCode.MIS_MATCHED_PASSWORD_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAuthCodeException.class)
    public MemberErrorResponseDto InvalidAuthCodeException(InvalidAuthCodeException e) {
        return new MemberErrorResponseDto(MemberErrorCode.INVALID_AUTH_CODE_ERROR, e.getMessage());
    }
}