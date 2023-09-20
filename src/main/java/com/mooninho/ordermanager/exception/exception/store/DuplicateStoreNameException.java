package com.mooninho.ordermanager.exception.exception.store;

import com.mooninho.ordermanager.exception.ApplicationException;
import com.mooninho.ordermanager.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateStoreNameException extends ApplicationException {

    public DuplicateStoreNameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_STORE_NAME_ERROR, "이미 존재하는 가게명입니다.");
    }
}
