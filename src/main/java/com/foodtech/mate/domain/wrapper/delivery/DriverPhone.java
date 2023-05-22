package com.foodtech.mate.domain.wrapper.delivery;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class DriverPhone {

    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "-를 포함한 휴대폰번호")
    private final String DriverPhone;

    private DriverPhone(String driverPhone) {
        DriverPhone = driverPhone;
    }

    public static DriverPhone of(String driverPhone) {

        if (driverPhone == null || driverPhone.isBlank()) {
            throw new IllegalArgumentException("배달원 휴대폰번호를 입력하세요");
        }

        return new DriverPhone(driverPhone);
    }
}
