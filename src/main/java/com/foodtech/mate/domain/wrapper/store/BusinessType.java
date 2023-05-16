package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class BusinessType {

    @Pattern(regexp = "/^[가-힣]{2,20}$/", message = "업태 2~20자의 한글")
    private final String businessType;

    private BusinessType(String businessType) {
        this.businessType = businessType;
    }

    public static BusinessType of(String businessType) {

        if (businessType == null || businessType.isBlank()) {
            throw new IllegalArgumentException("업태를 입력해주세요");
        }
        return new BusinessType(businessType);
    }
}
