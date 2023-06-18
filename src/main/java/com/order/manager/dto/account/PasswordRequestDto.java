package com.order.manager.dto.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordRequestDto {

    private String userId;
    private String phone;
    private String password;
    private String confirmPassword;
}
