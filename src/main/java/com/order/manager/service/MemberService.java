package com.order.manager.service;

import com.order.manager.domain.entity.Account;
import com.order.manager.domain.wrapper.account.Password;
import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.UserId;
import com.order.manager.dto.account.AccountDto;
import com.order.manager.exception.exception.member.MemberNotFoundException;
import com.order.manager.exception.exception.member.PhoneExistException;
import com.order.manager.exception.exception.member.SamePasswordException;
import com.order.manager.exception.exception.member.UserIdExistException;
import com.order.manager.repository.MemberQueryRepository;
import com.order.manager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.order.manager.util.validation.PatternMatcher.isInvalidPasswordFormat;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

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

    public String generateVerification() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    @Transactional
    public String findUserId(String phone) {

        UserId foundUserId = memberQueryRepository.findUserIdByPhone(Phone.of(phone));
        if (foundUserId == null) {
            throw new MemberNotFoundException();
        }
        return foundUserId.getUserId();
    }

    @Transactional
    public Account findAccount(String userId) {

        Account foundAccount = memberQueryRepository.findAccountByUserId(UserId.of(userId));
        if (foundAccount == null) {
            throw new MemberNotFoundException();
        }
        return foundAccount;
    }

    @Transactional
    public void changePassword(String userId, String password) {

        AccountDto foundAccount = memberQueryRepository.findAccountInfoByUserId(UserId.of(userId));

        if (isInvalidPasswordFormat(password)) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }
        if (isMatchesPassword(password, foundAccount)) {
            throw new SamePasswordException();
        }
        UserId userid = UserId.of(foundAccount.getUserId());
        Password newPassword = Password.encodedPassword(passwordEncoder.encode(password));

        memberQueryRepository.changePassword(userid, newPassword);
    }

    private boolean isMatchesPassword(String password, AccountDto foundAccount) {
        return passwordEncoder.matches(password, foundAccount.getPassword());
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
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }
    }
}
