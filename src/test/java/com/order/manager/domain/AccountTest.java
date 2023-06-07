package com.order.manager.domain;

import com.order.manager.domain.wrapper.account.Name;
import com.order.manager.domain.wrapper.account.Password;
import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    void nullOrEmptyUserId_throwException(String userId) {
        //given & when
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 비밀번호 미입력시 예외 발생")
    void nullOrEmptyPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 이름 미입력시 예외 발생")
    void nullOrEmptyName_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("회원 정보 입력 - 연락처 미입력시 예외 발생")
    void nullOrEmptyPhone_throwException(String phone) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(phone));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "test 123", "test123!"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 아이디 입력시 예외 발생")
    void invalidUserId_throwException(String userId) {
        //given & when
        Throwable throwable = catchThrowable(() -> UserId.of(userId));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"testPassword", "testPassword123", "testPassword!", "12345678!", "test123!", "test Password123!"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 패스워드 입력시 예외 발생")
    void invalidPassword_throwException(String password) {
        //given & when
        Throwable throwable = catchThrowable(() -> Password.of(password));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"김", "김 코딩", "a김코딩", "김코딩메이트", "codingKim"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 이름 입력시 예외 발생")
    void invalidName_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"01012345678", "02-1234-5678", "010-123-456", "110-123-4567"})
    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 연락처 입력시 예외 발생")
    void invalidPhone_throwException(String phone) {
        //given & when
        Throwable throwable = catchThrowable(() -> Phone.of(phone));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @Test
    @DisplayName("회원 정보 입력 - 올바른 아이디 입력시 아이디 객체 반환")
    void properUserId_returnUserIdObject() {
        //given & when
        String userId = "test123";
        UserId packedUserId = UserId.of(userId);

        //then
        assertThat(packedUserId).isNotNull();
        assertThat(packedUserId).isInstanceOf(UserId.class);
        assertThat(packedUserId.getUserId()).isEqualTo("test123");
    }

    @Test
    @DisplayName("회원 정보 입력 - 올바른 비밀번호 입력시 비밀번호 객체 반환")
    void properUserId_returnPasswordObject() {
        //given & when
        String userId = "testPassword123!";
        Password packedPassword = Password.of(userId);

        //then
        assertThat(packedPassword).isNotNull();
        assertThat(packedPassword).isInstanceOf(Password.class);
        assertThat(packedPassword.getPassword()).isEqualTo("testPassword123!");
    }

    @Test
    @DisplayName("회원 정보 입력 - 올바른 이름 입력시 이름 객체 반환")
    void properName_returnNameObject() {
        //given & when
        String userId = "김코딩";
        Name packedName = Name.of(userId);

        //then
        assertThat(packedName).isNotNull();
        assertThat(packedName).isInstanceOf(Name.class);
        assertThat(packedName.getName()).isEqualTo("김코딩");
    }

    @Test
    @DisplayName("회원 정보 입력 - 올바른 연락처 입력시 연락처 객체 반환")
    void properPhone_returnPhoneObject() {
        //given & when
        String userId = "010-1234-5678";
        Phone packedPhone = Phone.of(userId);

        //then
        assertThat(packedPhone).isNotNull();
        assertThat(packedPhone).isInstanceOf(Phone.class);
        assertThat(packedPhone.getPhone()).isEqualTo("010-1234-5678");
    }

}
