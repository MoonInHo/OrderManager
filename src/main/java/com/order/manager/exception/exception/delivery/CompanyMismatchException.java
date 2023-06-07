package com.order.manager.exception.exception.delivery;

public class CompanyMismatchException extends RuntimeException {

    public CompanyMismatchException() {
        super("요청한 배달업체와 일치하지 않습니다.");
    }
}
