package com.mooninho.ordermanager.ownerapp.delivery.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DriverPhone {

    private final String DriverPhone;

    private DriverPhone(String driverPhone) {
        DriverPhone = driverPhone;
    }

    public static DriverPhone of(String driverPhone) {

        if (driverPhone == null || driverPhone.isEmpty()) {
            throw new IllegalArgumentException("배달원 휴대폰번호를 입력하세요");
        }

        if (!driverPhone.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요");
        }

        return new DriverPhone(driverPhone);
    }
}