package com.mooninho.ordermanager.ownerapp.delivery.application.service;

import com.mooninho.ordermanager.ownerapp.delivery.application.dto.request.CreateDeliveryRequestDto;
import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.delivery.domain.repository.DeliveryRepository;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 배달 서비스")
class DeliveryServiceTest {

    private CreateDeliveryRequestDto createDeliveryRequestDto;

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    @BeforeEach
    void setCreateDeliveryRequestDto() {
        createDeliveryRequestDto = new CreateDeliveryRequestDto(1L, 3000);
    }

    @ParameterizedTest
    @EnumSource(value = DeliveryStatus.class, names = {"DELIVERING", "DISPATCH", "COMPLETE"})
    @DisplayName("배달 기사 배정 - 배달 대기중이 아닐때 배달원 할당시 예외 발생")
    void deliveryStatusIsNotWaiting_AssignmentDriver_throwException(DeliveryStatus deliveryStatus) {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(deliveryStatus));

        //when
        Throwable throwable = catchThrowable(
                () -> deliveryService.deliveryDriverAssignment(1L, 1L)
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("배달 기사 배정 - 대기중인 배달에 배달원 할당시 배달 상태 배차로 변경")
    void waitingDelivery_AssignmentDriver_updateDeliveryStatusToDispatch() {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(DeliveryStatus.WAITING));

        //when
        deliveryService.deliveryDriverAssignment(1L, 1L);

        //then
        verify(deliveryRepository, times(1)).updateDeliveryToDispatch(1L, 1L);
    }

    @ParameterizedTest
    @EnumSource(value = DeliveryStatus.class, names = {"WAITING", "DELIVERING", "COMPLETE"})
    @DisplayName("배달 픽업 - 배달 배차 상태가 아닐때 픽업 처리시 예외 발생")
    void deliveryStatusIsNotDispatch_updateToDelivering_throwException(DeliveryStatus deliveryStatus) {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(deliveryStatus));

        //when
        Throwable throwable = catchThrowable(
                () -> deliveryService.deliveryPickUp(1L, 1L)
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("배달 픽업 - 배차된 배달원이 아닐때 픽업 처리시 예외 발생")
    void notOwnerOfDelivery_updateToDelivering_throwException() {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(DeliveryStatus.DISPATCH));
        given(deliveryRepository.isDeliveryOwner(1L, 1L)).willReturn(false);

        //when
        Throwable throwable = catchThrowable(
                () -> deliveryService.deliveryPickUp(1L, 1L)
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("배달 픽업 - 올바른 배달원이 픽업 처리시 배달 상태 배달중으로 변경")
    void properDriver_updateToDelivering_updateDeliveryStatusToDelivering() {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(DeliveryStatus.DISPATCH));
        given(deliveryRepository.isDeliveryOwner(1L, 1L)).willReturn(true);

        //when
        deliveryService.deliveryPickUp(1L, 1L);

        //then
        verify(deliveryRepository, times(1)).updateDeliveryStatus(1L, DeliveryStatus.DELIVERING);
    }

    @ParameterizedTest
    @EnumSource(value = DeliveryStatus.class, names = {"WAITING", "DISPATCH", "COMPLETE"})
    @DisplayName("배달 완료 - 배달중 상태가 아닐때 배달 완료 처리시 예외 발생")
    void deliveryStatusIsNotDelivering_updateToComplete_throwException(DeliveryStatus deliveryStatus) {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(deliveryStatus));

        //when
        Throwable throwable = catchThrowable(
                () -> deliveryService.deliveryComplete(1L, 1L)
        );

        //then
        assertThat(throwable).isInstanceOf(InvalidRequestException.class);
        assertThat(throwable).hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("배달 픽업 - 배차된 배달원이 아닐때 배달 완료 처리시 예외 발생")
    void notOwnerOfDelivery_updateToComplete_throwException() {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(DeliveryStatus.DELIVERING));
        given(deliveryRepository.isDeliveryOwner(1L, 1L)).willReturn(false);

        //when
        Throwable throwable = catchThrowable(
                () -> deliveryService.deliveryComplete(1L, 1L)
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("배달 픽업 - 올바른 배달원이 배달 완료 처리시 배달 상태 배달 완료로 변경")
    void properDriver_updateToComplete_updateDeliveryStatusToComplete() {
        //given
        given(deliveryRepository.getDeliveryStatus(any())).willReturn(Optional.of(DeliveryStatus.DELIVERING));
        given(deliveryRepository.isDeliveryOwner(1L, 1L)).willReturn(true);

        //when
        deliveryService.deliveryComplete(1L, 1L);

        //then
        verify(deliveryRepository, times(1)).updateDeliveryStatus(1L, DeliveryStatus.COMPLETE);
    }
}