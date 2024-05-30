package com.mooninho.ordermanager.ownerapp.menu.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 메뉴명 도메인")
class MenuNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("메뉴명 입력 - 메뉴명 미입력시 예외 발생")
    void nullAndEmptyMenuName_throwException(String menuName) {
        //given & when
        Throwable throwable = catchThrowable(() -> MenuName.of(menuName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("메뉴명을 입력하세요.");
    }

    @Test
    @DisplayName("메뉴명 입력 - 적절한 형식의 메뉴명 입력시 메뉴명 객체 생성")
    void properMenuNameFormat_createMenuNameObject() {
        //given & when
        MenuName menuName = MenuName.of("알리오 올리오");

        //then
        assertThat(menuName).isInstanceOf(MenuName.class);
        assertThat(menuName.getMenuName()).isEqualTo("알리오 올리오");
    }
}