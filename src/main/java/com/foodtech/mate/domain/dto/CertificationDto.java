package com.foodtech.mate.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CertificationDto {

    private String phone;
    private String certificationCode;

    private CertificationDto(String phone, String certificationCode) {
        this.phone = phone;
        this.certificationCode = certificationCode;
    }

    public static CertificationDto createCertificationDto(String phone, String certificationCode) {

        return new CertificationDto(phone, certificationCode);
    }
}
