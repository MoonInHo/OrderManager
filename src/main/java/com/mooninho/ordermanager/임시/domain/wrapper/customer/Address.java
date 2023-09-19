package com.mooninho.ordermanager.임시.domain.wrapper.customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Address {

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