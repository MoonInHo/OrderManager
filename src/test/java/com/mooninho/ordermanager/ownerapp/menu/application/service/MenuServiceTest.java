package com.mooninho.ordermanager.ownerapp.menu.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.ownerapp.exception.exception.menu.DuplicateMenuNameException;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.menu.application.dto.request.CreateMenuRequestDto;
import com.mooninho.ordermanager.ownerapp.menu.domain.repository.MenuRepository;
import com.mooninho.ordermanager.ownerapp.store.domain.repository.StoreRepository;
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
@DisplayName("[유닛 테스트] - 메뉴 서비스")
class MenuServiceTest {

    private CreateMenuRequestDto createMenuRequestDto;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setCreateMenuRequestDto() {
        createMenuRequestDto = new CreateMenuRequestDto(
                "알리오 올리오",
                15000
        );
    }

    @Test
    @DisplayName("메뉴 생성 - 이미 존재하는 메뉴명으로 메뉴 생성시 예외 발생")
    void duplicateMenuName_createMenu_throwException() {
        //given
        given(menuRepository.isExistMenuName(any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(
                () -> menuService.createMenu(1L, createMenuRequestDto, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(DuplicateMenuNameException.class);
        assertThat(throwable).hasMessage("이미 존재하는 메뉴 입니다.");
    }

    @Test
    @DisplayName("메뉴 생성 - 가게의 주인이 아닌 사용자가 메뉴 생성시 예외 발생")
    void nonOwner_createMenu_throwException() {
        //given
        given(menuRepository.isExistMenuName(any())).willReturn(false);
        given(storeRepository.isNotOwner(any(), any())).willReturn(true);

        //when
        Throwable throwable = catchThrowable(
                () -> menuService.createMenu(1L, createMenuRequestDto, "user123")
        );

        //then
        assertThat(throwable).isInstanceOf(UnauthorizedException.class);
        assertThat(throwable).hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("메뉴 생성 - 가게의 주인이 적절한 정보로 메뉴 생성시 메뉴 정보 저장")
    void storeOwner_createMenu_saveMenuInfo() {
        //given
        given(menuRepository.isExistMenuName(any())).willReturn(false);
        given(storeRepository.isNotOwner(any(), any())).willReturn(false);

        //when
        menuService.createMenu(1L, createMenuRequestDto, "user123");

        //then
        verify(menuRepository, times(1)).save(any());
    }
}