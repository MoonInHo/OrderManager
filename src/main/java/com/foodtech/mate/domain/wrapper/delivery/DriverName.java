package com.foodtech.mate.domain.wrapper.delivery;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DriverName {

    private final String driverName;

    private DriverName(String driverName) {
        this.driverName = driverName;
    }

    public static DriverName of(String driverName) {
        return new DriverName(driverName);
    }
}
