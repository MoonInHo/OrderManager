package com.order.manager.dto.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PasswordRequestDto {

    private String userId;
    private String phone;
    private String password;
    private String confirmPassword;
}
