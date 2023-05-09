package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class StoreNumber {

    @Pattern(regexp = "\"^[0-9]{4}$\"")
    private final Integer storeNumber;

    private StoreNumber(Integer storeNumber) {
        this.storeNumber = storeNumber;
    }

    public static StoreNumber of(Integer storeNumber) {
        return new StoreNumber(storeNumber);
    }
}