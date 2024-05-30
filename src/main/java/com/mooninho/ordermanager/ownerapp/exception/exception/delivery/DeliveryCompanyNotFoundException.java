package com.mooninho.ordermanager.ownerapp.exception.exception.delivery;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DeliveryCompanyNotFoundException extends ApplicationException {

    public DeliveryCompanyNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_DELIVERY_COMPANY_ERROR, "배달 업체를 찾을 수 없습니다.");
    }
}
