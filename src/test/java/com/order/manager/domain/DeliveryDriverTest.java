package com.order.manager.domain;

import com.order.manager.domain.entity.DeliveryDriver;
import com.order.manager.domain.wrapper.delivery.DriverName;
import com.order.manager.domain.wrapper.delivery.DriverPhone;
import com.order.manager.dto.deliveryDriver.DeliveryDriverDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 배달기사 도메인")
public class DeliveryDriverTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("배달기사 정보 입력 - 배달기사 이름 미입력시 예외 발생")
    void nullDriverName_throwException(String driverName) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverName.of(driverName));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("배달원 이름을 입력하세요");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("배달기사 정보 입력 - 배달기사 연락처 미입력시 예외 발생")
    void nullDriverPhone_throwException(String driverPhone) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverPhone.of(driverPhone));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("배달원 휴대폰번호를 입력하세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"01012345678", "02-1234-5678", "010-123-456", "110-123-4567"})
    @DisplayName("배달기사 정보 입력 - 올바르지 않은 형식의 배달기사 연락처 입력시 예외 발생")
    void invalidDeliveryDriverPhone_throwException(String driverPhone) {
        //given & when
        Throwable throwable = catchThrowable(() -> DriverPhone.of(driverPhone));

        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("! 올바른 형식으로 입력해주세요");
    }

    @Test
    @DisplayName("배달기사 정보 입력 - 올바른 형식의 정보 입력시 배달기사 객체 생성")
    void properFormatInfo_createDeliveryDriverObject() {
        //given & when
        DeliveryDriverDto deliveryDriverDto = new DeliveryDriverDto("김배달", "010-1234-4321", 1L);
        DeliveryDriver deliveryDriver = deliveryDriverDto.toEntity();

        //then
        assertThat(deliveryDriver).isNotNull();
    }
}
