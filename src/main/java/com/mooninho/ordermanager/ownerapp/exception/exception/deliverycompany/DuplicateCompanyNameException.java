package com.mooninho.ordermanager.ownerapp.exception.exception.deliverycompany;

import com.mooninho.ordermanager.ownerapp.exception.ApplicationException;
import com.mooninho.ordermanager.ownerapp.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateCompanyNameException extends ApplicationException {

    public DuplicateCompanyNameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_COMPANY_NAME_ERROR, "이미 존재하는 배달 업체명입니다.");
    }
}
