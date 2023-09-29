package com.mooninho.ordermanager.ownerapp.menu.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.menu.domain.vo.MenuName;

public interface MenuQueryRepository {

    boolean isExistMenuName(MenuName menuName);
}
