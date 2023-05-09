package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class BusinessNumber {

    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{10}$", message = "사업자등록번호 10자리 입력해주세요.")
    private final Integer businessNumber;

    private BusinessNumber(Integer businessNumber) {
        this.businessNumber = businessNumber;
    }

    public static BusinessNumber of(Integer businessNumber) {

        if (businessNumber == null) {
            throw new IllegalArgumentException("사업자등록번호를 입력해주세요");
        }
        return new BusinessNumber(businessNumber);
    }
}