package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public Long signUp(Account account) {

        boolean userIdExist = memberQueryRepository.findByUserId(account.usernameOf());
        if (userIdExist) {
            throw new IllegalStateException("! 이미 사용중인 아이디 입니다.");
        }
        
        Account createdAccount = memberRepository.save(account);
        return createdAccount.getId();
    }
}
