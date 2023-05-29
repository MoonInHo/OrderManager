package com.foodtech.mate.domain.wrapper.customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Address {

    @Pattern(regexp = "/^((\\S+[시군구])(\\S+))+$/")
    private final String address;
    private final String addressDetail;

    private Address(String address, String addressDetail) {
        this.address = address;
        this.addressDetail = addressDetail;
    }

    public static Address of(String address, String addressDetail) {

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("주소를 입력해주세요");
        }

        if (addressDetail == null || addressDetail.isBlank()) {
            throw new IllegalArgumentException("상세주소를 입력해주세요");
        }

        return new Address(address, addressDetail);
    }
}