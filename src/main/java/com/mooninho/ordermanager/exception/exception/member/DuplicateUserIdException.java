package com.mooninho.ordermanager.exception.exception.member;

import com.mooninho.ordermanager.exception.ApplicationException;
import com.mooninho.ordermanager.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateUserIdException extends ApplicationException {

    public DuplicateUserIdException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_USERID_ERROR, "이미 존재하는 아이디입니다.");
    }
}
