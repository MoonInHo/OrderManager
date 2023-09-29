package com.mooninho.ordermanager.ownerapp.menu.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.global.UnauthorizedException;
import com.mooninho.ordermanager.ownerapp.exception.exception.owner.OwnerNotFoundException;
import com.mooninho.ordermanager.ownerapp.exception.exception.menu.DuplicateMenuNameException;
import com.mooninho.ordermanager.ownerapp.owner.domain.repository.OwnerRepository;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Username;
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
    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createMenu(Long storeId, CreateMenuRequestDto createMenuRequestDto, String username) {

        checkDuplicateMenuName(createMenuRequestDto.getMenuName());
        validateOwner(storeId, username);

        menuRepository.save(createMenuRequestDto.toEntity(storeId));
    }

    @Transactional(readOnly = true)
    protected void checkDuplicateMenuName(String menuName) {

        boolean existMenuName = menuRepository.isExistMenuName(MenuName.of(menuName));
        if (existMenuName) {
            throw new DuplicateMenuNameException();
        }
    }

    @Transactional(readOnly = true)
    protected void validateOwner(Long storeId, String username) {

        Long ownerId = ownerRepository.getOwnerId(Username.of(username))
                .orElseThrow(OwnerNotFoundException::new);

        boolean notOwner = storeRepository.isNotOwner(storeId, ownerId);
        if (notOwner) {
            throw new UnauthorizedException();
        }
    }
}
