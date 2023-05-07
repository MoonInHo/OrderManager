package com.foodtech.mate.domain.dto;

import lombok.Getter;

@Getter
public class ValidationOfCertificationRequestInfoDto {

    private String userId;
    private String phone;

    private ValidationOfCertificationRequestInfoDto(String userId, String phone) {
        this.userId = userId;
        this.phone = phone;
    }

    public static ValidationOfCertificationRequestInfoDto of(String userId, String phone) {
        return new ValidationOfCertificationRequestInfoDto(userId, phone);
    }
}
