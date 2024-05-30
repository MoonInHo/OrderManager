package com.mooninho.ordermanager.ownerapp.member.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.owner.DuplicatePhoneException;
import com.mooninho.ordermanager.ownerapp.exception.exception.owner.DuplicateUsernameException;
import com.mooninho.ordermanager.ownerapp.member.application.dto.request.CreateMemberRequestDto;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 회원 서비스")
class MemberServiceTest {

    private CreateMemberRequestDto createMemberRequestDto;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setCreateMemberRequestDto() {
        createMemberRequestDto = new CreateMemberRequestDto(
                "user123",
                "testPassword123!",
                "김개발",
                "010-1234-5678"
        );
    }

    @Test
    @DisplayName("회원 가입 - 이미 존재하는 아이디로 회원 가입 시도시 예외 발생")
    void duplicateUsername_signUp_throwException() {
        //given
        given(memberRepository.isUsernameExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(createMemberRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicateUsernameException.class);
        assertThat(throwable).hasMessage("이미 존재하는 아이디입니다.");
    }

    @Test
    @DisplayName("회원 가입 - 이미 존재하는 연락처로 회원 가입 시도시 예외 발생")
    void duplicatePhone_signUp_throwException() {
        //given
        given(memberRepository.isUsernameExist(any())).willReturn(false);
        given(memberRepository.isPhoneExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(createMemberRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicatePhoneException.class);
        assertThat(throwable).hasMessage("해당 연락처로 가입정보가 존재합니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 사용자 정보로 회원가입 시도시 회원 정보 생성")
    void properUserInfo_signUp_createMemberInfo() {
        //given
        given(memberRepository.isUsernameExist(any())).willReturn(false);
        given(memberRepository.isPhoneExist(any())).willReturn(false);

        //when
        memberService.signUp(createMemberRequestDto);

        //then
        verify(memberRepository, times(1)).save(any());
    }
}