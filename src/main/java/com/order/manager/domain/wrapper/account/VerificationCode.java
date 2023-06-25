package com.order.manager.domain.wrapper.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class VerificationCode {

    @Column(nullable = false)
    private final String verificationCode;

    public static VerificationCode of(String verificationCode) {
        return new VerificationCode(verificationCode);
    }
}
