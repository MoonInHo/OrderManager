package com.mooninho.ordermanager.menu.infrastructure.repository;

import com.mooninho.ordermanager.menu.domain.vo.MenuName;

public interface MenuQueryRepository {

    boolean isExistMenuName(MenuName menuName);
}
