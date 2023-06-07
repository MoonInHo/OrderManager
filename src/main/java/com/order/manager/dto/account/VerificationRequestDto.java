package com.order.manager.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequestDto {

    private String userId;
    private String name;
    private String phone;
    private String verificationCode;
}
