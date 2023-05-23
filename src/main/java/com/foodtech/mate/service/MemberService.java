package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.account.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.domain.wrapper.account.UserId;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        boolean userIdExist = memberQueryRepository.isUserIdExist(UserId.of(userId));
        if (userIdExist) {
            throw new IllegalStateException("! 이미 사용중인 아이디 입니다.");
        }

        Account account = AccountDto.toEntity(accountDto);
        account.encryptPassword(passwordEncoder.encode(account.passwordOf()));
        Account createdAccount = memberRepository.save(account);

        return createdAccount.getId();
    }

    @Transactional
    public String generateVerification() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    @Transactional
    public String findUserId(String phone) {

        UserId foundUserId = memberQueryRepository.findUserIdByPhone(Phone.of(phone));
        if (foundUserId == null) {
            throw new EntityNotFoundException("주문접수 앱 가입 정보가 없습니다.");
        }
        return foundUserId.getUserId();
    }

    @Transactional
    public Account findAccount(String userId) {

        Account foundAccount = memberQueryRepository.findAccountByUserId(userId);
        if (foundAccount == null) {
            throw new EntityNotFoundException("주문접수 앱 가입 정보가 없습니다.");
        }
        return foundAccount;
    }

    @Transactional
    public void changePassword(String userId, String password) {

        Account foundAccount = memberQueryRepository.findAccountByUserId(userId);
        if (isMatchesPassword(password, foundAccount)) {
            throw new IllegalArgumentException("기존 비밀번호로 변경하실 수 없습니다.");
        }
        foundAccount.encryptPassword(passwordEncoder.encode(password));

        memberQueryRepository.changePassword(foundAccount);
    }

    private boolean isMatchesPassword(String password, Account foundAccount) {
        return passwordEncoder.matches(password, foundAccount.passwordOf());
    }
}
