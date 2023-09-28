package com.mooninho.ordermanager.delivery.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DriverName {

    private final String driverName;

    private DriverName(String driverName) {
        this.driverName = driverName;
    }

    public static DriverName of(String driverName) {

        if (driverName == null || driverName.isEmpty()) {
            throw new IllegalArgumentException("배달원 이름을 입력하세요");
        }
        return new DriverName(driverName);
    }
}
