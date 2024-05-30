package com.mooninho.ordermanager.ownerapp.deliverydriver.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 배달원 이름 도메인")
class DriverNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("배달원 이름 입력 - 배달원 이름 미입력시 예외 발생")
    void nullAndEmptyDriverName_throwException(String driverName) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverName.of(driverName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("배달원 이름을 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"김 배달", "김배 달", " 김배달", "김배달 "})
    @DisplayName("배달원 이름 입력 - 배달원 이름에 공백 포함시 예외 발생")
    void nameContainsWhitespace_throwException(String driverName) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverName.of(driverName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름엔 공백을 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("배달원 이름 입력 - 올바른 형식의 배달원 이름 입력시 배달원 이름 객체 생성")
    void properNameFormat_createNameObject() {
        //given & when
        DriverName driverName = DriverName.of("김배달");

        //then
        assertThat(driverName).isInstanceOf(DriverName.class);
        assertThat(driverName.getDriverName()).isEqualTo("김배달");
    }
}