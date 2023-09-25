package com.mooninho.ordermanager.exception.exception.order;

import com.mooninho.ordermanager.exception.ApplicationException;
import com.mooninho.ordermanager.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmptyOrderListException extends ApplicationException {

    public EmptyOrderListException() {
        super(HttpStatus.NO_CONTENT, ErrorCode.EMPTY_ORDER_LIST_ERROR, "주문 목록이 비어있습니다.");
    }
}
