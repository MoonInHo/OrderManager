package com.order.manager.exception.exception.verification;

public class VerificationBlockedException extends RuntimeException {

    public VerificationBlockedException() {
        super("사용자 인증 횟수 초과. 고객센터에 문의 해주세요.");
    }
}
