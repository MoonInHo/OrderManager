package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class StoreContact {

    @NotBlank
    @Pattern(regexp = "/^\\d{9,11}$/", message = "매장 전화번호 9~11자의 숫자")
    private final String StoreContact;

    private StoreContact(String storeContact) {
        StoreContact = storeContact;
    }

    public static StoreContact of(String storeContact) {

        if (storeContact == null || storeContact.isBlank()) {
            throw new IllegalArgumentException("매장 전화번호를 입력해 주세요");
        }
        return new StoreContact(storeContact);
    }
}
