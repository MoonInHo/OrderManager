package com.foodtech.mate.domain;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 회원 도메인")
public class AccountTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 아이디 미입력시 예외 발생")
    void nullOrEmptyUsername_throwException(String username) {
        //given & when
        AccountDto accountDto = AccountDto.saveAccountInfo(username, "testPassword123!");
        Throwable throwable = catchThrowable(() -> Account.createMember(accountDto));

        //then
        assertThat(throwable).isInstanceOf(NullPointerException.class);
        assertThat(throwable).hasMessage("아이디를 입력하세요.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 아이디 미입력시 예외 발생")
    void nullOrEmptyPassword_throwException(String password) {
        //given & when
        AccountDto accountDto = AccountDto.saveAccountInfo("test123", password);
        Throwable throwable = catchThrowable(() -> Account.createMember(accountDto));

        //then
        assertThat(throwable).isInstanceOf(NullPointerException.class);
        assertThat(throwable).hasMessage("비밀번호를 입력하세요.");
    }
}
