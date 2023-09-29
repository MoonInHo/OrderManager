package com.mooninho.ordermanager.ownerapp.owner.application.security;

import com.mooninho.ordermanager.ownerapp.owner.domain.entity.Owner;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Username;
import com.mooninho.ordermanager.ownerapp.owner.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Owner owner = ownerRepository.findByUsername(Username.of(username))
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인해주세요."));

        return new AccountContext(owner, owner.createRole());
    }
}
