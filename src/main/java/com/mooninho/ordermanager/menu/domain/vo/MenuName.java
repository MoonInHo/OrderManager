package com.mooninho.ordermanager.menu.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class MenuName {

    private final String menuName;

    private MenuName(String menuName) {
        this.menuName = menuName;
    }

    public static MenuName of(String menuName) {

        if (menuName == null || menuName.isBlank()) {
            throw new IllegalArgumentException("메뉴명을 입력하세요.");
        }
        return new MenuName(menuName);
    }
}