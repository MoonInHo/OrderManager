package com.foodtech.mate.security.service;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberQueryRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Account account = memberQueryRepository.findAccountByUserId(userId);
        if (account == null) {
            throw new UsernameNotFoundException("사용자 정보를 다시 확인해 주세요.");
        }

        // TODO 학습하기: 묻지 말고 시켜라(Tell, Don't ask)
        return new AccountContext(account, account.createRole());
    }
}
