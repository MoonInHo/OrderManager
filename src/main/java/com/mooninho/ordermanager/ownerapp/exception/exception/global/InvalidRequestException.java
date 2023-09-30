package com.mooninho.ordermanager.ownerapp.exception.exception.global;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ApplicationException {

    public InvalidRequestException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_REQUEST, "잘못된 요청입니다.");
    }
}
