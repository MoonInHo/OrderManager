package com.order.manager.exception.exception.verification;

public class VerificationNotFoundException extends RuntimeException {

    public VerificationNotFoundException() {
        super("사용자 인증 후 다시 시도해주세요.");
    }
}
