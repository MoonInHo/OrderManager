package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public Long signUp(Account account) {

        boolean userIdExist = memberQueryRepository.isUsernameExist(account.usernameOf());
        if (userIdExist) {
            throw new IllegalStateException("! 이미 사용중인 아이디 입니다.");
        }
        Account createdAccount = memberRepository.save(account);
        return createdAccount.getId();
    }

    @Transactional
    public String createCertificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    @Transactional
    public String findUsername(String phone) {

        Account foundUsername = memberQueryRepository.findUsernameByPhone(phone);
        if (foundUsername == null) {
            throw new UsernameNotFoundException("주문접수 앱 가입 정보가 없습니다.");
        }
        return foundUsername.usernameOf();
    }
}
