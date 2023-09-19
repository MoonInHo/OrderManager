package com.mooninho.ordermanager.member.application.security;

import com.mooninho.ordermanager.member.domain.vo.UserId;
import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(UserId.of(userId))
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인해주세요."));

        return new AccountContext(member, member.createRole());
    }
}
