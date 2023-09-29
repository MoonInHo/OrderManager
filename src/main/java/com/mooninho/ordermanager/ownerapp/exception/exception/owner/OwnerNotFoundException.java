package com.mooninho.ordermanager.ownerapp.exception.exception.owner;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class OwnerNotFoundException extends ApplicationException {

    public OwnerNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_USER_ERROR, "존재하지 않는 회원입니다.");
    }
}
