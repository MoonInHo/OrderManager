package com.mooninho.ordermanager.ownerapp.customer.application.service;

import com.mooninho.ordermanager.ownerapp.customer.application.dto.CreateCustomerRequestDto;
import com.mooninho.ordermanager.ownerapp.customer.domain.repository.CustomerRepository;
import com.mooninho.ordermanager.ownerapp.exception.exception.customer.DuplicateContactException;
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
@DisplayName("[유닛 테스트] - 고객 서비스")
class CustomerServiceTest {

    private CreateCustomerRequestDto createCustomerRequestDto;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setCreateCustomerRequestDto() {
        createCustomerRequestDto = new CreateCustomerRequestDto(
                "테스트 주소",
                "테스트 상세 주소",
                "010-1234-5678"
        );
    }

    @Test
    @DisplayName("고객 생성 - 이미 존재하는 연락처로 고객 생성시 예외 발생")
    void duplicateContact_createCustomer_throwException() {
        //given
        given(customerRepository.isExistContact(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> customerService.createCustomer(createCustomerRequestDto));

        //then
        assertThat(throwable).isInstanceOf(DuplicateContactException.class);
        assertThat(throwable).hasMessage("해당 연락처를 가진 고객 정보가 이미 존재합니다.");
    }

    @Test
    @DisplayName("고객 생성 - 올바른 정보로 고객 생성시 고객 정보 저장")
    void properInfo_createCustomer_saveCustomerInfo() {
        //given
        given(customerRepository.isExistContact(any())).willReturn(false);

        //when
        customerService.createCustomer(createCustomerRequestDto);

        //then
        verify(customerRepository, times(1)).save(any());
    }
}