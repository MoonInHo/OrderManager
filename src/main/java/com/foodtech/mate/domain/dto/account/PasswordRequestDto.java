package com.foodtech.mate.domain.dto.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequestDto {

    private String password;
    private String confirmPassword;
}
