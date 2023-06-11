package com.order.manager.domain.wrapper.customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        if (isNullAndEmptyAddress(address)) {
            throw new IllegalArgumentException("주소를 입력해주세요");
        }

        if (isNullAndEmptyAddress(addressDetail)) {
            throw new IllegalArgumentException("상세주소를 입력해주세요");
        }

        return new Address(address, addressDetail);
    }

    private static boolean isNullAndEmptyAddress(String address) {
        return address == null || address.isBlank();
    }
}