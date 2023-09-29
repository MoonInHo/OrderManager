package com.mooninho.ordermanager.ownerapp.store.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class StoreName {

    private final String storeName;

    private StoreName(String storeName) {
        this.storeName = storeName;
    }

    public static StoreName of(String storeName) {

        if (storeName == null || storeName.isBlank()) {
            throw new IllegalArgumentException("가게명을 입력하세요.");
        }
        return new StoreName(storeName);
    }
}
