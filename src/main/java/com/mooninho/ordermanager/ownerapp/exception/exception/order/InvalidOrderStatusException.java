package com.mooninho.ordermanager.ownerapp.exception.exception.order;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidOrderStatusException extends ApplicationException {

    public InvalidOrderStatusException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_REQUEST, "잘못된 요청입니다.");
    }
}
