package com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 배달 업체명 도메인")
class CompanyNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("배달 업체명 입력 - 배달 업체명 미입력시 예외 발생")
    void nullAndEmptyCompanyName_throwException(String companyName) {
        //given & when
        Throwable throwable = catchThrowable(() -> CompanyName.of(companyName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("배달 업체명을 입력하세요.");
    }

    @Test
    @DisplayName("배달 업체명 입력 - 올바른 형식의 배달 업체명 입력시 배달 업체명 객체 생성")
    void properCompanyNameFormat_createCompanyNameObject() {
        //given & when
        CompanyName companyName = CompanyName.of("배달의 민족");

        //then
        assertThat(companyName).isInstanceOf(CompanyName.class);
        assertThat(companyName.getCompanyName()).isEqualTo("배달의 민족");
    }
}