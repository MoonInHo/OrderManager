package com.order.manager.domain;

import com.order.manager.domain.entity.Customer;
import com.order.manager.domain.wrapper.customer.Address;
import com.order.manager.domain.wrapper.customer.Contact;
import com.order.manager.dto.customer.CustomerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 고객 도메인")
public class CustomerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("고객 정보 입력 - 주소 미입력시 예외 발생")
    void nullAndEmptyAddress_throwException(String address) {
        //given & when
        Throwable throwable = catchThrowable(() -> Address.of(address, "1층 로비"));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("주소를 입력해주세요");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("고객 정보 입력 - 상세 주소 미입력시 예외 발생")
    void nullAndEmptyAddressDetail_throwException(String addressDetail) {
        //given & when
        Throwable throwable = catchThrowable(() -> Address.of("경기도 김포시 풍무로 128", addressDetail));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("상세주소를 입력해주세요");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("고객 정보 입력 - 연락처 미입력시 예외 발생")
    void nullContact_throwException(String contact) {
        //given & when
        Throwable throwable = catchThrowable(() -> Contact.of(contact));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 연락처를 입력해주세요.");
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("고객 정보 입력 - 연락처 공백 사용시 예외 발생")
    void EmptyContact_throwExcetpion(String contact) {
        //given & when
        Throwable throwable = catchThrowable(() -> Contact.of(contact));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 공백을 사용할 수 없습니다.");
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"서울", "서울특별시", "서울특별시 강남구", "서울특별시 강남구"})
//    @DisplayName("고객 정보 입력 - 올바르지 않은 형식의 주소 입력시 예외 발생")
//    void invalidAddress_throwException(String address) {
//        given & when
//        Throwable throwable = catchThrowable(() -> Address.of(address, "1층 로비"));
//
//        then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
//        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요");
//    }

    @ParameterizedTest
    @ValueSource(strings = {"01012345678", "010.1234.5678", "02-1234-1234", "010-123-456"})
    @DisplayName("고객 정보 입력 - 올바르지 않은 연락처 입력시 예외 발생")
    void invalidContact_throwException(String contact) {
        //given & when
        Throwable throwable = catchThrowable(() -> Contact.of(contact));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요.");
    }

    @Test
    @DisplayName("고객 정보 입력 - 올바른 정보 입력시 고객 객체 생성")
    void properFormatInfo_createCustomerObject() {
        //given & when
        CustomerDto customerDto = new CustomerDto("경기 김포시 풍무로146번길", "1층 로비", "010-1234-1234");
        Customer customer = customerDto.toEntity();

        //then
        assertThat(customer).isNotNull();
    }
}
