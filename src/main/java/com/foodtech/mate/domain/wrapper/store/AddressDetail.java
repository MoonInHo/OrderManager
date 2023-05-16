package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class AddressDetail {

    @Pattern(regexp = "/^[가-힣0-9\\s\\-(),#]*$/")
    private final String addressDetail;

    private AddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public static AddressDetail of(String addressDetail) {

        if (addressDetail == null || addressDetail.isBlank()) {
            throw new IllegalArgumentException("상세주소를 입력해주세요");
        }
        return new AddressDetail(addressDetail);
    }
}
