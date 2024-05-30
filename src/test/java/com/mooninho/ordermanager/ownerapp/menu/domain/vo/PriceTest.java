package com.mooninho.ordermanager.ownerapp.menu.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 가격 도메인")
class PriceTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("가격 입력 - 가격 미입력시 예외 발생")
    void nullPrice_throwException(Integer price) {
        //given & when
        Throwable throwable = catchThrowable(() -> Price.of(price));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("가격을 입력하세요.");
    }

    @Test
    @DisplayName("가격 입력 - 올바른 형식의 가격 입력시 가격 객체 생성")
    void properPriceFormat_createPriceObject() {
        //given & when
        Price price = Price.of(15000);

        //then
        assertThat(price).isInstanceOf(Price.class);
        assertThat(price.getPrice()).isEqualTo(15000);
    }
}