package com.mooninho.ordermanager.ownerapp.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 이름 도메인")
class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름 입력 - 이름 미입력시 예외 발생")
    void nullAndEmptyName_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름을 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"김 개발", "김개 발", " 김개발", "김개발 ", " "})
    @DisplayName("이름 입력 - 이름에 공백 포함시 예외 발생")
    void nameContainsWhitespace_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"김개발입니다", "김개발.", "김개발1", "GaeBalKim", "김Dev"})
    @DisplayName("이름 입력 - 이름 형식이 올바르지 않을 경우 예외 발생")
    void invalidNameFormat_throwException(String name) {
        //given & when
        Throwable throwable = catchThrowable(() -> Name.of(name));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("이름 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("이름 입력 - 올바른 형식의 이름 입력시 이름 객체 생성")
    void properNameFormat_createNameObject() {
        //given & when
        Name name = Name.of("김개발");

        //then
        assertThat(name).isInstanceOf(Name.class);
        assertThat(name.getName()).isEqualTo("김개발");
    }
}