package com.mooninho.ordermanager.ownerapp.order.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 고객 요청 도메인")
class CustomerRequestTest {

    @Test
    @DisplayName("고객 요청 입력 - 고객 요청 최대 글자 수 초과시 예외 발생")
    void customerRequestExceedsMaxLength_throwException() {
        //given & when
        Throwable throwable = catchThrowable(
                () -> CustomerRequest.of("abcdefghijklmnopqrstuvwxyz1234567891011121314151617")
        );

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("50글자 이내로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"수저포크 필요 없어요.", "문 앞에 놓고 벨 눌러주세요.", "아이가 자고 있으니 문 앞에 놓고 문자 주세요."})
    @DisplayName("고객 요청 입력 - 올바른 형식의 고객 요청 입력시 고객 요청 객체 생성")
    void properCustomerRequestFormat_createCustomerRequestObject(String customerRequest) {
        //given & when
        CustomerRequest customerRequestObject = CustomerRequest.of(customerRequest);

        //then
        assertThat(customerRequestObject).isInstanceOf(CustomerRequest.class);
    }
}