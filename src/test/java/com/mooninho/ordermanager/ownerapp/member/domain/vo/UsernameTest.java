package com.mooninho.ordermanager.ownerapp.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 아이디 도메인")
class UsernameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("아이디 입력 - 아이디 미입력시 예외 발생")
    void nullAndEmptyUsername_throwException(String username) {
        //given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" user123", "user123 ", " user 123", "us er123", " "})
    @DisplayName("아이디 입력 - 아이디에 공백 포함시 예외 발생")
    void usernameContainsWhitespace_throwException(String username) {
        //given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"testUser", "user", "1234", "김회원", "김회원123"})
    @DisplayName("아이디 입력 - 아이디 형식이 올바르지 않을 경우 예외 발생")
    void invalidUsernameFormat_throwException(String username) {
        //given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("아이디 입력 - 올바른 형식의 아이디 입력시 아이디 객체 생성")
    void properUsernameFormat_createNameObject() {
        //given & when
        Username username = Username.of("user123");

        //then
        assertThat(username).isInstanceOf(Username.class);
        assertThat(username.username()).isEqualTo("user123");
    }
}