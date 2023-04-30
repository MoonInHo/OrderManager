package com.foodtech.mate.exception.handler;

import com.foodtech.mate.exception.exception.InvalidPasswordException;
import com.foodtech.mate.exception.exception.InvalidUsernameException;
import com.foodtech.mate.exception.code.MemberErrorCode;
import com.foodtech.mate.exception.dto.ErrorResponseDto;
import com.foodtech.mate.exception.exception.NullAndBlankPasswordException;
import com.foodtech.mate.exception.exception.NullAndBlankUsernameException;
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
    @ExceptionHandler(InvalidUsernameException.class)
    public ErrorResponseDto invalidUserIdException(InvalidUsernameException e) {
        return new ErrorResponseDto(MemberErrorCode.INVALID_USERNAME, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPasswordException.class)
    public ErrorResponseDto invalidPasswordException(InvalidPasswordException e) {
        return new ErrorResponseDto(MemberErrorCode.INVALID_PASSWORD, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullAndBlankUsernameException.class)
    public ErrorResponseDto nullUsernameException(NullAndBlankUsernameException e) {
        return new ErrorResponseDto(MemberErrorCode.NULL_AND_BLANK_USERNAME, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullAndBlankPasswordException.class)
    public ErrorResponseDto nullPasswordException(NullAndBlankPasswordException e) {
        return new ErrorResponseDto(MemberErrorCode.NULL_AND_BLANK_PASSWORD, e.getMessage());
    }
}