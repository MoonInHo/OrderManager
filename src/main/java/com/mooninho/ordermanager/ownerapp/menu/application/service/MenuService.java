package com.mooninho.ordermanager.ownerapp.menu.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.ownerapp.exception.exception.menu.DuplicateMenuNameException;
import com.mooninho.ordermanager.ownerapp.exception.exception.owner.MemberNotFoundException;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;
import com.mooninho.ordermanager.ownerapp.menu.application.dto.request.CreateMenuRequestDto;
import com.mooninho.ordermanager.ownerapp.menu.domain.repository.MenuRepository;
import com.mooninho.ordermanager.ownerapp.menu.domain.vo.MenuName;
import com.mooninho.ordermanager.ownerapp.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createMenu(Long storeId, CreateMenuRequestDto createMenuRequestDto, String username) {

        checkDuplicateMenuName(createMenuRequestDto.getMenuName());
        checkStoreOwnership(storeId, username);

        menuRepository.save(createMenuRequestDto.toEntity(storeId));
    }

    protected void checkDuplicateMenuName(String menuName) {
        boolean existMenuName = menuRepository.isExistMenuName(MenuName.of(menuName));
        if (existMenuName) {
            throw new DuplicateMenuNameException();
        }
    }

    protected void checkStoreOwnership(Long storeId, String username) {

        Long memberId = memberRepository.getMemberId(Username.of(username));

        boolean notOwner = storeRepository.isNotOwner(storeId, memberId);
        if (notOwner) {
            throw new UnauthorizedException();
        }
    }
}
