package com.mooninho.ordermanager.ownerapp.customer.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 고객 연락처 도메인")
class ContactTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("고객 연락처 입력 - 고객 연락처 미입력시 예외 발생")
    void nullAndEmptyPhone_throwException(String contact) {
        //given & when
        Throwable throwable = catchThrowable(() -> Contact.of(contact));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("고객 연락처를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"010.1234.5678", "01012345678", "010 1234 5678", "016-1234-5678"})
    @DisplayName("고객 연락처 입력 - 고객 연락처 형식이 올바르지 않을 경우 예외 발생")
    void invalidPhoneFormat_throwException(String contact) {
        //given & when
        Throwable throwable = catchThrowable(() -> Contact.of(contact));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("고객 연락처 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("고객 연락처 입력 - 고객 올바른 형식의 연락처 입력시 연락처 객체 생성")
    void properPhoneFormat_createPhoneObject() {
        //given & when
        Contact contact = Contact.of("010-1234-5678");

        //then
        assertThat(contact).isInstanceOf(Contact.class);
        assertThat(contact.getContact()).isEqualTo("010-1234-5678");
    }
}