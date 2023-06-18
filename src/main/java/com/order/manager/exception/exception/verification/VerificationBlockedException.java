package com.order.manager.exception.exception.verification;

public class VerificationBlockedException extends RuntimeException {

    public VerificationBlockedException() {
        super("사용자 인증이 잠겼습니다.");
    }
}
