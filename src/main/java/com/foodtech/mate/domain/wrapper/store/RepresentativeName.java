package com.foodtech.mate.domain.wrapper.store;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class RepresentativeName {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$", message = "2~20자의 한글, 영문, 숫자로 입력해주세요.")
    private final String representativeName;

    private RepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public static RepresentativeName of(String representativeName) {

        if (representativeName == null || representativeName.isBlank()) {
            throw new IllegalArgumentException("대표자명을 입력해주세요");
        }
        return new RepresentativeName(representativeName);
    }
}
