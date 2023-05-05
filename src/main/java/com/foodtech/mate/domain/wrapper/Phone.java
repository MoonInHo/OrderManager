package com.foodtech.mate.domain.wrapper;

import com.foodtech.mate.exception.exception.InvalidPhoneException;
import com.foodtech.mate.exception.exception.NullAndBlankPhoneException;
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
            throw new NullAndBlankPhoneException("! 연락처를 입력해 주세요.");
        }

        if (phone.isBlank()) {
            throw new NullAndBlankPhoneException("! 공백을 사용할 수 없습니다.");
        }

        if (!phone.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new InvalidPhoneException("! 올바른 형식으로 입력해주세요.");
        }

        return new Phone(phone);
    }
}
