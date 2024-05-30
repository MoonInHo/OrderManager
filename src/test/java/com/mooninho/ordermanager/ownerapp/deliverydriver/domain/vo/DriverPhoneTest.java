package com.mooninho.ordermanager.ownerapp.deliverydriver.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 배달원 연락처 도메인")
class DriverPhoneTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("배달원 연락처 입력 - 배달원 연락처 미입력시 예외 발생")
    void nullAndEmptyDriverPhone_throwException(String driverPhone) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverPhone.of(driverPhone));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("배달원 휴대폰번호를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"010.1234.5678", "01012345678", "010 1234 5678", "016-1234-5678"})
    @DisplayName("배달원 연락처 입력 - 배달원 연락처 형식이 올바르지 않을 경우 예외 발생")
    void invalidDriverPhoneFormat_throwException(String driverPhone) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverPhone.of(driverPhone));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("연락처 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("배달원 연락처 입력 - 올바른 형식의 배달원 연락처 입력시 배달원 연락처 객체 생성")
    void properDriverPhoneFormat_createDriverPhoneObject() {
        //given & when
        DriverPhone driverPhone = DriverPhone.of("010-1234-5678");

        //then
        assertThat(driverPhone).isInstanceOf(DriverPhone.class);
        assertThat(driverPhone.getDriverPhone()).isEqualTo("010-1234-5678");
    }
}