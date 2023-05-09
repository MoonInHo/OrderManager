package com.foodtech.mate.controller.verifier;

import com.foodtech.mate.domain.dto.account.ChangePasswordDto;
import com.foodtech.mate.domain.dto.account.VerificationDto;
import com.foodtech.mate.domain.wrapper.account.Name;
import com.foodtech.mate.domain.wrapper.account.Password;
import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.domain.wrapper.account.UserId;
import com.foodtech.mate.exception.exception.InvalidAuthCodeException;
import com.foodtech.mate.exception.exception.MisMatchedPasswordException;
import com.foodtech.mate.exception.exception.VerificationFailureException;

import java.util.Map;
import java.util.Random;

public class Verifier {

    public static String generateUserIdVerificationCode(VerificationDto verificationDto, Map<String, String> verificationMap) {

        Phone.of(verificationDto.getPhone());
        return generateVerificationCode(verificationDto, verificationMap);
    }

    public static String generatePasswordVerificationCode(VerificationDto verificationDto, Map<String, String> verificationMap) {

        inputVerifier(verificationDto);
        return generateVerificationCode(verificationDto, verificationMap);
    }

    public static void verifyCode(VerificationDto verificationDto, Map<String, String> verificationMap) {

        String phone = verificationDto.getPhone();
        String verificationCode = verificationDto.getVerificationCode();
        String savedCode = verificationMap.get(phone);

        if (savedCode == null || !savedCode.equals(verificationCode)) {
            throw new InvalidAuthCodeException("인증번호가 일치하지 않습니다.");
        }
        verificationMap.remove(phone);
    }

    public static String verifyPassword(ChangePasswordDto changePasswordDto, Map<String, String> verificationMap) {

        String password = changePasswordDto.getPassword();
        String verifyPassword = changePasswordDto.getVerifyPassword();
        Password.of(password);
        Password.of(verifyPassword);

        if (!verificationMap.get("verification").equals("success")) {
            throw new VerificationFailureException("본인인증 후 다시 시도해주세요.");
        }

        if (!password.equals(verifyPassword)) {
            throw new MisMatchedPasswordException("비밀번호를 다시 확인해주세요.");
        }

        return password;
    }

    private static String generateVerificationCode(VerificationDto verificationDto, Map<String, String> verificationMap) {

        if (verificationMap.size() != 0) {
            verificationMap.remove(verificationDto.getPhone());
        }
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    private static void inputVerifier(VerificationDto verificationDto) {
        UserId.of(verificationDto.getUserId());
        Name.of(verificationDto.getName());
        Phone.of(verificationDto.getPhone());
    }
}
