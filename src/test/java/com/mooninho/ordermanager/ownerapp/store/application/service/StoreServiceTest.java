package com.mooninho.ordermanager.ownerapp.store.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.store.DuplicateStoreNameException;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.store.application.dto.request.CreateStoreRequestDto;
import com.mooninho.ordermanager.ownerapp.store.domain.repository.StoreRepository;
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
@DisplayName("[유닛 테스트] - 가게 서비스")
class StoreServiceTest {

    private CreateStoreRequestDto createStoreRequestDto;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    void setCreateStoreRequestDto() {
        createStoreRequestDto = new CreateStoreRequestDto("브런치페리아");
    }

    @Test
    @DisplayName("가게 생성 - 이미 존재하는 가게명으로 가게 생성시 예외 발생")
    void duplicateStoreName_createStore_throwException() {
        //given
        given(storeRepository.isExistStoreName(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> storeService.createStore(createStoreRequestDto, "user123"));

        //then
        assertThat(throwable).isInstanceOf(DuplicateStoreNameException.class);
        assertThat(throwable).hasMessage("이미 존재하는 가게명입니다.");
    }

    @Test
    @DisplayName("가게 생성 - 올바른 정보로 가게 생성시 가게 정보 저장")
    void properInfo_createStore_saveStoreInfo() {
        //given
        given(storeRepository.isExistStoreName(any())).willReturn(false);

        //when
        storeService.createStore(createStoreRequestDto, "user123");

        //then
        verify(storeRepository, times(1)).save(any());
    }
}