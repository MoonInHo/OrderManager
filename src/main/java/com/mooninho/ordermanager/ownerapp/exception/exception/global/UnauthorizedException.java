package com.mooninho.ordermanager.ownerapp.exception.exception.global;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED_POSTING_ERROR, "접근 권한이 없습니다.");
    }
}
