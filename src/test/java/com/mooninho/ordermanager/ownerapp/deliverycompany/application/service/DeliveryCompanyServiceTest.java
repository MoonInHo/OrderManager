package com.mooninho.ordermanager.ownerapp.deliverycompany.application.service;

import com.mooninho.ordermanager.ownerapp.deliverycompany.application.dto.request.CreateDeliveryCompanyRequestDto;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.repository.DeliveryCompanyRepository;
import com.mooninho.ordermanager.ownerapp.exception.exception.deliverycompany.DuplicateCompanyNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 배달 업체 서비스")
class DeliveryCompanyServiceTest {

    private CreateDeliveryCompanyRequestDto createDeliveryCompanyRequestDto;

    @Mock
    private DeliveryCompanyRepository deliveryCompanyRepository;

    @InjectMocks
    private DeliveryCompanyService deliveryCompanyService;

    @BeforeEach
    void setCreateDeliveryCompanyRequestDto() {
        createDeliveryCompanyRequestDto = new CreateDeliveryCompanyRequestDto("BAROGO");
    }

    @Test
    @DisplayName("배달 업체 생성 - 이미 존재하는 배달 업체명으로 배달 업체 생성 시도시 예외 발생")
    void duplicateDeliveryCompanyName_createDeliveryCompany_throwException() {
        //given
        given(deliveryCompanyRepository.isCompanyNameExist(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(
                () -> deliveryCompanyService.createDeliveryCompany(1L, createDeliveryCompanyRequestDto)
        );

        //then
        assertThat(throwable).isInstanceOf(DuplicateCompanyNameException.class);
        assertThat(throwable).hasMessage("이미 존재하는 배달 업체명입니다.");
    }

    @Test
    @DisplayName("배달 업체 생성 - 올바른 정보로 배달 업체 생성 시도시 배달 업체 정보 저장")
    void properInfo_createDeliveryCompany_saveDeliveryCompanyInfo() {
        //given
        given(deliveryCompanyRepository.isCompanyNameExist(any())).willReturn(false);

        //when
        deliveryCompanyService.createDeliveryCompany(1L , createDeliveryCompanyRequestDto);

        //then
        verify(deliveryCompanyRepository, times(1)).save(any());
    }
}