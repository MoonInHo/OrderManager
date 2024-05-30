package com.mooninho.ordermanager.ownerapp.deliverydriver.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DriverPhone {

    private final String driverPhone;

    private DriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public static DriverPhone of(String driverPhone) {

        if (driverPhone == null || driverPhone.isBlank()) {
            throw new IllegalArgumentException("배달원 휴대폰번호를 입력하세요.");
        }
        if (!driverPhone.matches("^010-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new IllegalArgumentException("연락처 형식이 올바르지 않습니다.");
        }

        return new DriverPhone(driverPhone);
    }
}
