package com.mooninho.ordermanager.ownerapp.exception.exception.deliverydriver;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundDeliveryDriverException extends ApplicationException {

    public NotFoundDeliveryDriverException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_DELIVERY_DRIVER_ERROR, "배달 기사를 찾을 수 없습니다.");
    }
}
