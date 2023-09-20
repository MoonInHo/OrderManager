package com.mooninho.ordermanager.임시.dto.menu;

import com.mooninho.ordermanager.임시.domain.entity.Menu;
import com.mooninho.ordermanager.store.domain.entity.Store;
import com.mooninho.ordermanager.임시.domain.wrapper.menu.MenuName;
import com.mooninho.ordermanager.임시.domain.wrapper.menu.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

    private String menuName;
    private Integer price;
    private Long storeId;

    public Menu toEntity() {
        return Menu.createMenu(MenuName.of(menuName), Price.of(price), Store.createKeyValue(storeId));
    }
}
