package com.order.manager.service;

import com.order.manager.domain.entity.Account;
import com.order.manager.domain.entity.Verification;
import com.order.manager.domain.wrapper.account.*;
import com.order.manager.dto.account.AccountDto;
import com.order.manager.dto.account.AccountResponseDto;
import com.order.manager.dto.account.PasswordRequestDto;
import com.order.manager.dto.account.VerificationRequestDto;
import com.order.manager.enums.VerificationType;
import com.order.manager.exception.exception.InvalidFormatException;
import com.order.manager.exception.exception.member.MemberNotFoundException;
import com.order.manager.exception.exception.member.PhoneExistException;
import com.order.manager.exception.exception.member.SamePasswordException;
import com.order.manager.exception.exception.member.UserIdExistException;
import com.order.manager.exception.exception.verification.VerificationCodeNotFoundException;
import com.order.manager.exception.exception.verification.VerificationNotFoundException;
import com.order.manager.repository.MemberQueryRepository;
import com.order.manager.repository.MemberRepository;
import com.order.manager.repository.VerificationQueryRepository;
import com.order.manager.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final VerificationRepository verificationRepository;
    private final VerificationQueryRepository verificationQueryRepository;

    @Transactional
    public void signUp(AccountDto accountDto) {

        String userId = accountDto.getUserId();
        String password = accountDto.getPassword();
        String phone = accountDto.getPhone();

        signUpInfoValidation(userId, password, phone);

        accountDto.passwordEncrypt(passwordEncoder.encode(password));
        Account account = accountDto.toEntity();
        memberRepository.save(account);
    }

    public String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    @Transactional
    public void saveVerificationToUserId(VerificationRequestDto verificationRequestDto, String verificationCode) {

        Phone phone = Phone.of(verificationRequestDto.getPhone());

        boolean verificationExist = verificationQueryRepository.isVerificationExist(phone);
        if (verificationExist) {
            verificationQueryRepository.deleteVerificationByPhone(phone);
        }
        Verification verification = verificationRequestDto.toEntity(phone, verificationCode, VerificationType.USERID);

        verificationRepository.save(verification);
    }

    @Transactional(noRollbackFor = VerificationCodeNotFoundException.class)
    public void validateVerificationCodeUserId(Phone phone, VerificationCode verificationCode) {

        boolean notMatchedVerificationCode = verificationQueryRepository.notMatchedVerificationCode(phone, verificationCode);

        if (notMatchedVerificationCode) {
            throw new VerificationCodeNotFoundException();
        }
    }

    @Transactional
    public String findUserIdByPhone(Phone phone) {

        UserId foundUserId = memberQueryRepository.findUserIdByPhone(phone);
        if (foundUserId == null) {
            throw new MemberNotFoundException();
        }
        return foundUserId.isUserId();
    }

    @Transactional
    public void saveVerificationToPassword(VerificationRequestDto verificationRequestDto, String verificationCode) {

        UserId.of(verificationRequestDto.getUserId());
        Name.of(verificationRequestDto.getName());
        Phone phone = Phone.of(verificationRequestDto.getPhone());

        boolean verificationExist = verificationQueryRepository.isVerificationExist(phone);
        if (verificationExist) {
            verificationQueryRepository.deleteVerificationByPhone(phone);
        }
        Verification verification = verificationRequestDto.toEntity(phone, verificationCode, VerificationType.PASSWORD);

        verificationRepository.save(verification);
    }

    @Transactional(noRollbackFor = VerificationCodeNotFoundException.class)
    public void validateVerificationCodeToPassword(VerificationRequestDto verificationRequestDto) {

        Phone phone = Phone.of(verificationRequestDto.getPhone());
        VerificationCode verificationCode = VerificationCode.of(verificationRequestDto.getVerificationCode());

        boolean notMatchedVerificationCode = verificationQueryRepository.notMatchedVerificationCode(phone, verificationCode);
        if (notMatchedVerificationCode) {
            verificationQueryRepository.deleteVerificationByPhone(phone);
            throw new VerificationCodeNotFoundException();
        }
        verificationQueryRepository.updateVerificationStatus(phone);
    }

    @Transactional
    public void findAccount(VerificationRequestDto verificationRequestDto) {

        Phone phone = Phone.of(verificationRequestDto.getPhone());

        boolean accountNotExist = memberQueryRepository.isAccountNotExist(phone);
        if (accountNotExist) {
            throw new MemberNotFoundException();
        }
    }

    @Transactional
    public void deleteVerificationInfo(Phone phone) {
        verificationQueryRepository.deleteVerificationByPhone(phone);
    }

    @Transactional
    public void checkVerification(Phone phone) {
        boolean verificationInfoNotExist = verificationQueryRepository.isVerificationInfoNotExist(phone);
        if (verificationInfoNotExist) {
            throw new VerificationNotFoundException();
        }
    }

    @Transactional
    public void changePassword(PasswordRequestDto passwordRequestDto) {

        UserId userId = UserId.of(passwordRequestDto.getUserId());
        Password password = Password.of(passwordRequestDto.getPassword());

        AccountResponseDto foundAccount = memberQueryRepository.findByUserId(userId);

        if (isMatchesPassword(password, foundAccount)) {
            throw new SamePasswordException();
        }

        Password newPassword = Password.encodedPassword(passwordEncoder.encode(password.isPassword()));

        memberQueryRepository.changePassword(userId, newPassword);
    }

    private boolean isMatchesPassword(Password password, AccountResponseDto foundAccount) {
        return passwordEncoder.matches(password.isPassword(), foundAccount.isPassword());
    }

    private void signUpInfoValidation(String userId, String password, String phone) {

        boolean userIdExist = memberQueryRepository.isUserIdExist(UserId.of(userId));
        if (userIdExist) {
            throw new UserIdExistException();
        }
        boolean phoneExist = memberQueryRepository.isPhoneExist(Phone.of(phone));
        if (phoneExist) {
            throw new PhoneExistException();
        }
        if (isInvalidPasswordFormat(password)) {
            throw new InvalidFormatException();
        }
    }

    public boolean isInvalidPasswordFormat(String password) {
        return !password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$");
    }
}
