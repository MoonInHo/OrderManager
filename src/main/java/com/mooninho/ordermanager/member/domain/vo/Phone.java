package com.mooninho.ordermanager.member.domain.vo;

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

        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("연락처를 입력하세요.");
        }
        if (phone.contains(" ")) {
            throw new IllegalArgumentException("연락처엔 공백을 포함할 수 없습니다.");
        }
        if (!phone.matches("^010-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new IllegalArgumentException("연락처 형식이 올바르지 않습니다.");
        }

        return new Phone(phone);
    }
}
