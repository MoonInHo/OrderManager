package com.order.manager.domain;

import com.order.manager.domain.entity.Menu;
import com.order.manager.domain.wrapper.menu.MenuName;
import com.order.manager.domain.wrapper.menu.Price;
import com.order.manager.dto.menu.MenuDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 메뉴 도메인")
public class MenuTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("메뉴 정보 입력 - 메뉴명 미입력시 예외 발생")
    void nullAndEmptyMenuName_throwException(String menuName) {
        //given & when
        Throwable throwable = catchThrowable(() -> MenuName.of(menuName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("메뉴명을 입력해주세요");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("메뉴 정보 입력 - 가격 미입력시 예외 발생")
    void nullPrice_throwException(Integer price) {
        //given & when
        Throwable throwable = catchThrowable(() -> Price.of(price));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("가격을 입력해주세요");
    }

    @Test
    @DisplayName("메뉴 정보 입력 - 올바른 형식의 정보 입력시 메뉴 객체 생성")
    void properFormatInfo_createMenuObject() {
        //given & when
        MenuDto menuDto = new MenuDto("알리오올리오", 12900, 1L);
        Menu menu = menuDto.toEntity();

        //then
        assertThat(menu).isNotNull();
    }
}
