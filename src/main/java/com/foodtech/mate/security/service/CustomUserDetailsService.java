package com.foodtech.mate.security.service;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberQueryRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = memberQueryRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("사용자 정보를 다시 확인해 주세요.");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}
