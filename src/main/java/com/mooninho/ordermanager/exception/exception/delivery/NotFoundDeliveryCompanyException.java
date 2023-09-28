package com.mooninho.ordermanager.exception.exception.delivery;

import com.mooninho.ordermanager.exception.ApplicationException;
import com.mooninho.ordermanager.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundDeliveryCompanyException extends ApplicationException {

    public NotFoundDeliveryCompanyException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_DELIVERY_COMPANY_ERROR, "배달 업체를 찾을 수 없습니다.");
    }
}
