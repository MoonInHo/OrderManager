package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
