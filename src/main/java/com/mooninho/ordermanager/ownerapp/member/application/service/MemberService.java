package com.mooninho.ordermanager.ownerapp.member.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.owner.DuplicatePhoneException;
import com.mooninho.ordermanager.ownerapp.exception.exception.owner.DuplicateUsernameException;
import com.mooninho.ordermanager.ownerapp.member.application.dto.request.CreateMemberRequestDto;
import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Phone;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(CreateMemberRequestDto createMemberRequestDto) {

        checkDuplicateUsername(createMemberRequestDto);
        checkDuplicatePhone(createMemberRequestDto);

        Member owner = createMemberRequestDto.toEntity();
        owner.passwordEncrypt(passwordEncoder);

        memberRepository.save(owner);
    }

    protected void checkDuplicateUsername(CreateMemberRequestDto createOwnerRequestDto) {

        boolean usernameExist = memberRepository.isUsernameExist(Username.of(createOwnerRequestDto.getUsername()));
        if (usernameExist) {
            throw new DuplicateUsernameException();
        }
    }

    protected void checkDuplicatePhone(CreateMemberRequestDto createOwnerRequestDto) {

        boolean phoneExist = memberRepository.isPhoneExist(Phone.of(createOwnerRequestDto.getPhone()));
        if (phoneExist) {
            throw new DuplicatePhoneException();
        }
    }
}
