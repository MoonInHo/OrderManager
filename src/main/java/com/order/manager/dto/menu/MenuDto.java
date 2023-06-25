package com.order.manager.dto.menu;

import com.order.manager.domain.entity.Menu;
import com.order.manager.domain.entity.Store;
import com.order.manager.domain.wrapper.menu.MenuName;
import com.order.manager.domain.wrapper.menu.Price;
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
