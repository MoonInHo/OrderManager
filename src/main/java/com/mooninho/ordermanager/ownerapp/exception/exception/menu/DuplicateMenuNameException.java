package com.mooninho.ordermanager.ownerapp.exception.exception.menu;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateMenuNameException extends ApplicationException {

    public DuplicateMenuNameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_MENU_NAME_ERROR, "이미 존재하는 메뉴 입니다.");
    }
}
