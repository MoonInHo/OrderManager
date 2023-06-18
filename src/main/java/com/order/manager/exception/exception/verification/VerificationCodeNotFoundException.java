package com.order.manager.exception.exception.verification;

public class VerificationCodeNotFoundException extends RuntimeException {

    public VerificationCodeNotFoundException() {
        super("유효하지 않은 인증번호입니다.");
    }
}
