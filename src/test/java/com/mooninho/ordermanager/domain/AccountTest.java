//package com.mooninho.ordermanager.domain;
//
//import com.mooninho.ordermanager.application.member.domain.entity.Member;
//import com.mooninho.ordermanager.application.member.domain.vo.Name;
//import com.mooninho.ordermanager.application.member.domain.vo.Password;
//import com.mooninho.ordermanager.application.member.domain.vo.Phone;
//import com.mooninho.ordermanager.application.member.domain.vo.UserId;
//import com.mooninho.ordermanager.application.member.application.dto.CreateMemberRequestDto;
//import com.mooninho.ordermanager.임시.exception.exception.EmptyValueException;
//import com.mooninho.ordermanager.임시.exception.exception.InvalidFormatException;
//import com.mooninho.ordermanager.임시.exception.exception.member.NullValueException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EmptySource;
//import org.junit.jupiter.params.provider.NullSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.ThrowableAssert.catchThrowable;
//import static org.mockito.Mockito.mock;
//
//@DisplayName("[유닛 테스트] - 회원 도메인")
//public class AccountTest {
//
//    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
//
//    @ParameterizedTest
//    @NullSource
//    @DisplayName("회원 정보 입력 - 아이디 미입력시 예외 발생")
//    void nullUserId_throwException(String userId) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> UserId.of(userId));
//
//        //then
//        assertThat(throwable).isInstanceOf(NullValueException.class);
//        assertThat(throwable).hasMessage("! 필수 잆력값 입니다.");
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    @DisplayName("회원 정보 입력 - 아이디 공백 입력시 예외 발생")
//    void emptyUserId_throwException(String userId) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> UserId.of(userId));
//
//        //then
//        assertThat(throwable).isInstanceOf(EmptyValueException.class);
//        assertThat(throwable).hasMessage("! 공백을 사용할 수 없습니다.");
//    }
//
//    @ParameterizedTest
//    @NullSource
//    @DisplayName("회원 정보 입력 - 비밀번호 미입력시 예외 발생")
//    void nullPassword_throwException(String password) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Password.of(password));
//
//        //then
//        assertThat(throwable).isInstanceOf(NullValueException.class);
//        assertThat(throwable).hasMessage("! 필수 잆력값 입니다.");
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    @DisplayName("회원 정보 입력 - 비밀번호 공백 입력시 예외 발생")
//    void emptyPassword_throwException(String password) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Password.of(password));
//
//        //then
//        assertThat(throwable).isInstanceOf(EmptyValueException.class);
//        assertThat(throwable).hasMessage("! 공백을 사용할 수 없습니다.");
//    }
//
//    @ParameterizedTest
//    @NullSource
//    @DisplayName("회원 정보 입력 - 이름 미입력시 예외 발생")
//    void nullName_throwException(String name) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Name.of(name));
//
//        //then
//        assertThat(throwable).isInstanceOf(NullValueException.class);
//        assertThat(throwable).hasMessage("! 필수 잆력값 입니다.");
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    @DisplayName("회원 정보 입력 - 이름 공백 입력시 예외 발생")
//    void emptyName_throwException(String name) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Name.of(name));
//
//        //then
//        assertThat(throwable).isInstanceOf(EmptyValueException.class);
//        assertThat(throwable).hasMessage("! 공백을 사용할 수 없습니다.");
//    }
//
//    @ParameterizedTest
//    @NullSource
//    @DisplayName("회원 정보 입력 - 연락처 미입력시 예외 발생")
//    void nullPhone_throwException(String phone) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Password.of(phone));
//
//        //then
//        assertThat(throwable).isInstanceOf(NullValueException.class);
//        assertThat(throwable).hasMessage("! 필수 잆력값 입니다.");
//    }
//
//    @ParameterizedTest
//    @EmptySource
//    @DisplayName("회원 정보 입력 - 연락처 공백 입력시 예외 발생")
//    void emptyPhone_throwException(String phone) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Password.of(phone));
//
//        //then
//        assertThat(throwable).isInstanceOf(EmptyValueException.class);
//        assertThat(throwable).hasMessage("! 공백을 사용할 수 없습니다.");
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"test", "test 123", "test123!"})
//    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 아이디 입력시 예외 발생")
//    void invalidUserId_throwException(String userId) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> UserId.of(userId));
//
//        //then
//        assertThat(throwable).isInstanceOf(InvalidFormatException.class);
//        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"testPassword", "testPassword123", "testPassword!", "12345678!", "test123!", "test Password123!"})
//    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 패스워드 입력시 예외 발생")
//    void invalidPassword_throwException(String password) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Password.of(password));
//
//        //then
//        assertThat(throwable).isInstanceOf(InvalidFormatException.class);
//        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"김", "김 코딩", "김코딩테스트", "codingKim"})
//    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 이름 입력시 예외 발생")
//    void invalidName_throwException(String name) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Name.of(name));
//
//        //then
//        assertThat(throwable).isInstanceOf(InvalidFormatException.class);
//        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"01012345678", "02-1234-5678", "010-123-456", "110-123-4567"})
//    @DisplayName("회원 정보 입력 - 올바르지 않은 형식의 연락처 입력시 예외 발생")
//    void invalidPhone_throwException(String phone) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> Phone.of(phone));
//
//        //then
//        assertThat(throwable).isInstanceOf(InvalidFormatException.class);
//        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
//    }
//
//    @Test
//    @DisplayName("회원 정보 입력 - 올바른 아이디 입력시 아이디 객체 생성")
//    void properUserId_returnUserIdObject() {
//        //given & when
//        UserId userId = UserId.of("test123");
//
//        //then
//        assertThat(userId).isNotNull();
//        assertThat(userId).isInstanceOf(UserId.class);
//        assertThat(userId.userId()).isEqualTo("test123");
//    }
//
//    @Test
//    @DisplayName("회원 정보 입력 - 올바른 비밀번호 입력시 비밀번호 객체 생성")
//    void properUserId_returnPasswordObject() {
//        //given & when
//        Password password = Password.of("testPassword123!");
//
//        //then
//        assertThat(password).isNotNull();
//        assertThat(password).isInstanceOf(Password.class);
//        assertThat(password.password()).isEqualTo("testPassword123!");
//    }
//
//    @Test
//    @DisplayName("회원 정보 입력 - 올바른 이름 입력시 이름 객체 생성")
//    void properName_returnNameObject() {
//        //given & when
//        Name name = Name.of("김코딩");
//
//        //then
//        assertThat(name).isNotNull();
//        assertThat(name).isInstanceOf(Name.class);
//        assertThat(name.getName()).isEqualTo("김코딩");
//    }
//
//    @Test
//    @DisplayName("회원 정보 입력 - 올바른 연락처 입력시 연락처 객체 생성")
//    void properPhone_returnPhoneObject() {
//        //given & when
//        Phone phone = Phone.of("010-1234-5678");
//
//        //then
//        assertThat(phone).isNotNull();
//        assertThat(phone).isInstanceOf(Phone.class);
//        assertThat(phone.getPhone()).isEqualTo("010-1234-5678");
//    }
//
//    @Test
//    @DisplayName("회원 정보 입력 - 올바른 형식의 정보 입력시 회원 객체 생성")
//    void properFormatInfo_createAccountObject() {
//        //given & when
//        CreateMemberRequestDto accountDto = CreateMemberRequestDto.createAccountDto("innovation123", "goodPassword123!", "김개발", "010-1234-5678");
//        accountDto.passwordEncrypt(passwordEncoder.encode(accountDto.getPassword()));
//        Member account = accountDto.toEntity();
//
//        //then
//        assertThat(account).isNotNull();
//    }
//}
