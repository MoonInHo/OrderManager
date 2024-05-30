package com.mooninho.ordermanager.ownerapp.exception.exception.owner;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends ApplicationException {

    public MemberNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.MEMBER_NOT_FOUND_ERROR, "존재하지 않는 회원입니다.");
    }
}
