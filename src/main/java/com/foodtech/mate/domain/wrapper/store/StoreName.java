package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class StoreName {

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,30}$", message = "2~30자의 한글, 영문, 숫자로 입력해주세요.")
    private final String storeName;

    private StoreName(String storeName) {
        this.storeName = storeName;
    }

    public static StoreName of(String storeName) {

        if (storeName == null || storeName.isBlank()) {
            throw new IllegalArgumentException("매장명을 입력해주세요");
        }
        return new StoreName(storeName);
    }
}
