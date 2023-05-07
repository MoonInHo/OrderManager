package com.foodtech.mate.domain.wrapper.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Phone {

    private final String phone;

    private Phone(String phone) {
        this.phone = phone;
    }

    public static Phone of(String phone) {

        if (phone == null) {
            throw new IllegalArgumentException("! 연락처를 입력해 주세요.");
        }

        if (phone.isBlank()) {
            throw new IllegalArgumentException("! 공백을 사용할 수 없습니다.");
        }

        if (!phone.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }

        return new Phone(phone);
    }
}
