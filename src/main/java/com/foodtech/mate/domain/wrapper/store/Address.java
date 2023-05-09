package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class Address {

    @NotBlank
    @Pattern(regexp = "/^((\\S+[시군구])(\\S+))+$/")
    private final String address;

    private Address(String address) {
        this.address = address;
    }

    public static Address of(String address) {

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("사업장주소를 입력해주세요");
        }
        return new Address(address);
    }
}
