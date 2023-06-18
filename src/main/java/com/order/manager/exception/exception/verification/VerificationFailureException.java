package com.order.manager.exception.exception.verification;

public class VerificationFailureException extends RuntimeException {

    public VerificationFailureException(Long count) {
        super("인증에 실패했습니다. 남은 횟수 : " + count + "/5 회");
    }
}
