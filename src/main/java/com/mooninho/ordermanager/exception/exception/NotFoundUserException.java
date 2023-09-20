package com.mooninho.ordermanager.exception.exception;

import com.mooninho.ordermanager.exception.ApplicationException;
import com.mooninho.ordermanager.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends ApplicationException {

    public NotFoundUserException() {
        super(HttpStatus.CONFLICT, ErrorCode.NOT_FOUND_USER_ERROR, "존재하지 않는 회원입니다.");
    }
}
