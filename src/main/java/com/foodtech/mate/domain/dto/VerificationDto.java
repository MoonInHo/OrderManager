package com.foodtech.mate.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VerificationDto {

    private String userId;
    private String name;
    private String phone;
    private String verificationCode;

    public static VerificationDto createVerificationDto(String userId, String name, String phone, String verificationCode) {
        return new VerificationDto(userId, name, phone, verificationCode);
    }
}
