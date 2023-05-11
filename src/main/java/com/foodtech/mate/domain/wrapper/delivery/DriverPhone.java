package com.foodtech.mate.domain.wrapper.delivery;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DriverPhone {

    private final String DriverPhone;

    private DriverPhone(String driverPhone) {
        DriverPhone = driverPhone;
    }

    public static DriverPhone of(String driverPhone) {
        return new DriverPhone(driverPhone);
    }
}
