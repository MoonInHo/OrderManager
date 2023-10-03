package com.mooninho.ordermanager.ownerapp.exception.exception.order;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundOrderException extends ApplicationException {

    public NotFoundOrderException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ORDER_ERROR, "이미 취소되었거나 존재하지 않는 주문입니다.");
    }
}
