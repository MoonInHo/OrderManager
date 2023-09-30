package com.mooninho.ordermanager.ownerapp.exception.exception.delivery;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundDeliveryException extends ApplicationException {

    public NotFoundDeliveryException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_DELIVERY_ERROR, "이미 처리중이거나 존재하지 않는 배달 요청입니다.");
    }
}
