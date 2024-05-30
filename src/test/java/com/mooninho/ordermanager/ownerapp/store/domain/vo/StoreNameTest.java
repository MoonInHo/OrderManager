package com.mooninho.ordermanager.ownerapp.store.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[유닛 테스트] - 가게명 도메인")
class StoreNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("가게명 입력 - 가게명 미입력시 예외 발생")
    void nullAndEmptyStoreName_throwException(String storeName) {
        //given & when
        Throwable throwable = catchThrowable(() -> StoreName.of(storeName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("가게명을 입력하세요.");
    }

    @Test
    @DisplayName("가게명 입력 - 올바른 형식의 가게명 입력시 가게명 객체 생성")
    void properStoreNameFormat_createStoreNameObject() {
        //given & when
        StoreName storeName = StoreName.of("브런치페리아");

        //then
        assertThat(storeName).isInstanceOf(StoreName.class);
        assertThat(storeName.getStoreName()).isEqualTo("브런치페리아");
    }
}