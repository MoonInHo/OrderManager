package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class Industry {

    @Pattern(regexp = "/^[가-힣]{2,20}$/", message = "업종 2~20자의 한글")
    private final String industry;

    private Industry(String industry) {
        this.industry = industry;
    }

    public static Industry of(String industry) {

        if (industry == null || industry.isBlank()) {
            throw new IllegalArgumentException("업종을 입력해주세요");
        }
        return new Industry(industry);
    }
}
