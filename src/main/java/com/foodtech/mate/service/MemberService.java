package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.domain.wrapper.account.UserId;
import com.foodtech.mate.dto.account.AccountDto;
import com.foodtech.mate.exception.exception.member.MemberNotFoundException;
import com.foodtech.mate.exception.exception.member.PhoneExistException;
import com.foodtech.mate.exception.exception.member.SamePasswordException;
import com.foodtech.mate.exception.exception.member.UserIdExistException;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
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

    @Transactional
    public Long signUp(AccountDto accountDto) {

        String userId = accountDto.getUserId();
        String phone = accountDto.getPhone();

        boolean userIdExist = memberQueryRepository.isUserIdExist(UserId.of(userId));
        if (userIdExist) {
            throw new UserIdExistException();
        }
        boolean phoneExist = memberQueryRepository.isPhoneExist(Phone.of(phone));
        if (phoneExist) {
            throw new PhoneExistException();
        }

        Account account = accountDto.toEntity();
        account.encryptPassword(passwordEncoder.encode(account.passwordOf()));
        Account createdAccount = memberRepository.save(account);

        return createdAccount.getId();
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

        Account foundAccount = memberQueryRepository.findAccountByUserId(UserId.of(userId));
        if (isMatchesPassword(password, foundAccount)) {
            throw new SamePasswordException();
        }
        foundAccount.encryptPassword(passwordEncoder.encode(password));

        memberQueryRepository.changePassword(foundAccount);
    }

    private boolean isMatchesPassword(String password, Account foundAccount) {
        return passwordEncoder.matches(password, foundAccount.passwordOf());
    }
}
