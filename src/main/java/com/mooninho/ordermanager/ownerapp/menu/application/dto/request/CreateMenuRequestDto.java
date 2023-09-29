package com.mooninho.ordermanager.ownerapp.menu.application.dto.request;

import com.mooninho.ordermanager.ownerapp.menu.domain.entity.Menu;
import com.mooninho.ordermanager.ownerapp.menu.domain.vo.MenuName;
import com.mooninho.ordermanager.ownerapp.menu.domain.vo.Price;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuRequestDto {

    private String menuName;
    private Integer price;

    public Menu toEntity(Long storeId) {
        return Menu.createMenu(
                MenuName.of(menuName),
                Price.of(price),
                Store.createKeyObject(storeId)
        );
    }
}
