package com.mooninho.ordermanager.ownerapp.deliverydriver.domain.vo;

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

        if (driverName == null || driverName.isBlank()) {
            throw new IllegalArgumentException("배달원 이름을 입력하세요");
        }
        if (driverName.contains(" ")) {
            throw new IllegalArgumentException("이름엔 공백을 포함할 수 없습니다.");
        }

        return new DriverName(driverName);
    }
}
