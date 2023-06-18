package com.order.manager.dto.account;

import com.order.manager.domain.entity.Verification;
import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.VerificationCode;
import com.order.manager.enums.VerificationType;
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

    public Verification toEntity(Phone phone, String verificationCode, VerificationType verificationType) {
        return Verification.createVerification(phone, VerificationCode.of(verificationCode), verificationType);
    }
}
