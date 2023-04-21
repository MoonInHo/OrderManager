package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("[유닛 테스트] - 회원 서비스")
public class MemberServiceTest {

    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository);

    @Test
    @DisplayName("회원 가입 - 이메일 중복시 예외 발생")
    void existsEmail_join_throwException() {
        //given
        AccountDto accountDto = AccountDto.saveAccountInfo("test123", "testPassword123!");
        Account account = Account.createMember(accountDto);
        given(memberQueryRepository.isUsernameExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signUp(account));

        //then
        assertThat(throwable).isInstanceOf(IllegalStateException.class);
        assertThat(throwable).hasMessage("! 이미 사용중인 아이디 입니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 회원정보로 가입시 회원생성")
    void properInfo_signUp_createMember() throws Exception {
        //given
        AccountDto accountDto = AccountDto.saveAccountInfo("test123", "testPassword123!");
        Account account = Account.createMember(accountDto);
        given(memberRepository.save(any()))
                .willReturn(account);

        // when
        Account createdAccount = memberRepository.save(account);

        //then
        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount).isInstanceOf(Account.class);
    }

    @Test
    @DisplayName("회원 로그인 - 존재하지 않는 아이디로 로그인 시도시 예외 발생")
    void nonexistentUsername_signIn_throwException() {
        //given
        AccountDto accountDto = AccountDto.saveAccountInfo("test1234", "testPassword123!");
        given(memberQueryRepository.findByUsername(any())).willReturn(null);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signIn(accountDto));

        //then
        assertThat(throwable).isInstanceOf(UsernameNotFoundException.class);
        assertThat(throwable).hasMessage("사용자 정보를 다시 확인해 주세요.");
    }

    @Test
    @DisplayName("회원 로그인 - 일치하지 않는 비밀번호로 로그인 시도시 예외 발생")
    void invalidPassword_signIn_throwException() {
        AccountDto savedAccount = AccountDto.saveAccountInfo("test123", "testPassword123!");
        AccountDto accountDto = AccountDto.saveAccountInfo("test123", "Password123!");
        Account account = Account.createMember(savedAccount);
        given(memberQueryRepository.findByUsername(any())).willReturn(account);

        //when
        Throwable throwable = catchThrowable(() -> memberService.signIn(accountDto));

        //then
        assertThat(throwable).isInstanceOf(BadCredentialsException.class);
        assertThat(throwable).hasMessage("비밀번호가 일치하지 않습니다.");
    }
}
