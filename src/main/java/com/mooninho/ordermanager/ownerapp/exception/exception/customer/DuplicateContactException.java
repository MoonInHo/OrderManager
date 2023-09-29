package com.mooninho.ordermanager.ownerapp.exception.exception.customer;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateContactException extends ApplicationException {

    public DuplicateContactException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_CONTACT_ERROR, "해당 연락처를 가진 고객 정보가 이미 존재합니다.");
    }
}
