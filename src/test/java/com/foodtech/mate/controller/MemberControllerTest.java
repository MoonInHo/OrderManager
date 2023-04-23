package com.foodtech.mate.controller;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.exception.exception.InvalidPasswordException;
import com.foodtech.mate.exception.exception.InvalidUserIdException;
import com.foodtech.mate.repository.MemberQueryRepository;
import com.foodtech.mate.repository.MemberRepository;
import com.foodtech.mate.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.mock;

@DisplayName("[유닛 테스트] - 회원 컨트롤러")
public class MemberControllerTest {

    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final MemberQueryRepository memberQueryRepository = mock(MemberQueryRepository.class);
    private final MemberService memberService = new MemberService(memberRepository, memberQueryRepository);
    private final MemberController memberController = new MemberController(passwordEncoder, memberService);

    @ParameterizedTest
    @ValueSource(strings = {"test", "test!", "test 1"})
    @DisplayName("회원가입 정보 입력 - 올바르지 않은 형식의 아이디 입력시 예외 발생")
    void invalidUserId_signUp_throwException(String username) throws Exception {
        //given
        AccountDto accountDto = AccountDto.saveAccountInfo(username, "testPassword123!");

        //when
        Throwable throwable = catchThrowable(() -> memberController.signUp(accountDto));

        //then
        assertThat(throwable).isInstanceOf(InvalidUserIdException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123!", "testPassword!", "testPassword123", "test Password123!"})
    @DisplayName("회원가입 정보 입력 - 올바르지 않은 형식의 비밀번호 입력시 예외 발생")
    void invalidPassword_signUp_throwException(String password) throws Exception {
        //given
        AccountDto accountDto = AccountDto.saveAccountInfo("test123", password);

        //when
        Throwable throwable = catchThrowable(() -> memberController.signUp(accountDto));

        //then
        assertThat(throwable).isInstanceOf(InvalidPasswordException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }
}
