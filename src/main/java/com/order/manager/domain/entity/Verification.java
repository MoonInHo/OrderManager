package com.order.manager.domain.entity;

import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.UserId;
import com.order.manager.domain.wrapper.account.VerificationCode;
import com.order.manager.enums.VerificationStatus;
import com.order.manager.enums.VerificationType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Phone phone;

    @Embedded
    private VerificationCode verificationCode;

    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    private Verification(Phone phone, VerificationCode verificationCode, VerificationType verificationType) {
        this.phone = phone;
        this.verificationCode = verificationCode;
        this.verificationType = verificationType;
        this.verificationStatus = VerificationStatus.WAITING;
    }

    public static Verification createVerification(Phone phone, VerificationCode verificationCode, VerificationType verificationType) {
        return new Verification(phone, verificationCode, verificationType);
    }
}
