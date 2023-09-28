//package com.mooninho.ordermanager.domain;
//
//import com.mooninho.ordermanager.delivery.domain.entity.Delivery;
//import com.mooninho.ordermanager.delivery.domain.vo.DeliveryTips;
//import com.mooninho.ordermanager.임시.dto.delivery.DeliveryDto;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.NullSource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.ThrowableAssert.catchThrowable;
//
//@DisplayName("[유닛 테스트] - 배달 도메인")
//public class DeliveryTest {
//
//    @ParameterizedTest
//    @NullSource
//    @DisplayName("배달 정보 입력 - 배달팁 미입력시 예외 발생")
//    void nullCompany_throwException(Integer deliveryTips) {
//        //given & when
//        Throwable throwable = catchThrowable(() -> DeliveryTips.of(deliveryTips));
//
//        //then
//        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
//        assertThat(throwable).hasMessage("배달팁을 입력하세요");
//    }
//
//    @Test
//    @DisplayName("배달 정보 입력 - 올바른 형식의 정보 입력시 배달 객체 생성")
//    void properFormatInfo_createDeliveryObject() {
//        //give & when
//        Delivery delivery = DeliveryDto.toEntity(1L, 1L, 3500);
//
//        //then
//        assertThat(delivery).isNotNull();
//    }
//}
