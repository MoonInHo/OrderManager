package com.mooninho.ordermanager.ownerapp.exception.exception.owner;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicatePhoneException extends ApplicationException {

    public DuplicatePhoneException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_PHONE_ERROR, "해당 연락처로 가입정보가 존재합니다.");
    }
}
