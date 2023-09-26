package com.mooninho.ordermanager.exception.exception.order;

import com.mooninho.ordermanager.exception.ApplicationException;
import com.mooninho.ordermanager.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundOrderException extends ApplicationException {

    public NotFoundOrderException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_ORDER_ERROR, "이미 삭제되었거나 존재하지 않는 주문입니다.");
    }
}
