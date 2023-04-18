package com.foodtech.mate.domain.wrapper;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class Password {

    @NotBlank(message = "! 공백을 사용할 수 없습니다.")
    private final String password;

    private Password() {
        this.password = "";
    }

    private Password(String password) {
        this.password = password;
    }

    public static Password of(String password) {
        return new Password(password);
    }
}
