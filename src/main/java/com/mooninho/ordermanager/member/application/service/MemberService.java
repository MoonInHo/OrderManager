package com.mooninho.ordermanager.member.application.service;

import com.mooninho.ordermanager.exception.exception.DuplicatePhoneException;
import com.mooninho.ordermanager.exception.exception.DuplicateUserIdException;
import com.mooninho.ordermanager.member.application.dto.CreateMemberRequestDto;
import com.mooninho.ordermanager.member.domain.vo.Phone;
import com.mooninho.ordermanager.member.domain.vo.UserId;
import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.member.domain.repository.MemberRepository;
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

        checkDuplicateUserId(createMemberRequestDto);
        checkDuplicatePhone(createMemberRequestDto);

        Member member = createMemberRequestDto.toEntity();
        member.passwordEncrypt(passwordEncoder);

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    protected void checkDuplicateUserId(CreateMemberRequestDto createMemberRequestDto) {

        boolean userIdExist = memberRepository.isUserIdExist(UserId.of(createMemberRequestDto.getUserId()));
        if (userIdExist) {
            throw new DuplicateUserIdException();
        }
    }

    @Transactional(readOnly = true)
    protected void checkDuplicatePhone(CreateMemberRequestDto createMemberRequestDto) {

        boolean phoneExist = memberRepository.isPhoneExist(Phone.of(createMemberRequestDto.getPhone()));
        if (phoneExist) {
            throw new DuplicatePhoneException();
        }
    }
}
