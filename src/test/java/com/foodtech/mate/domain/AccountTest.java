package com.foodtech.mate.domain;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.Password;
import com.foodtech.mate.domain.wrapper.Username;
import com.foodtech.mate.exception.exception.InvalidPasswordException;
import com.foodtech.mate.exception.exception.InvalidUsernameException;
import com.foodtech.mate.exception.exception.NullAndBlankPasswordException;
import com.foodtech.mate.exception.exception.NullAndBlankUsernameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 회원 도메인")
public class AccountTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 아이디 미입력시 예외 발생")
    void nullOrEmptyUsername_throwException(String username) {
        //given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        //then
        assertThat(throwable).isInstanceOf(NullAndBlankUsernameException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 비밀번호 미입력시 예외 발생")
    void nullOrEmptyPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(NullAndBlankPasswordException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "test 123", "test123!"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 아이디 입력시 예외 발생")
    void invalidUsername_throwException(String username) {
        //given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        //then
        assertThat(throwable).isInstanceOf(InvalidUsernameException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"testPassword", "testPassword123", "testPassword!", "12345678!", "test123!", "test Password123!"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 패스워드 입력시 예외 발생")
    void invalidPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(InvalidPasswordException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }


}
