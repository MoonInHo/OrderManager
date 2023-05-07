package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.UserId;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public Long signUp(Account account) {

        boolean userIdExist = memberQueryRepository.isUserIdExist(account.userIdOf());
        if (userIdExist) {
            throw new IllegalStateException("! 이미 사용중인 아이디 입니다.");
        }
        Account createdAccount = memberRepository.save(account);
        return createdAccount.getId();
    }

    @Transactional
    public String findUserId(String phone) {

        UserId foundUserId = memberQueryRepository.findUserIdByPhone(phone);
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
    public void changePassword(Account account) {
        memberQueryRepository.changePassword(account);
    }
}
