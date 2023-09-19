package com.mooninho.ordermanager.임시.dto.order;

import com.mooninho.ordermanager.임시.domain.wrapper.menu.MenuName;
import com.mooninho.ordermanager.임시.domain.wrapper.order.Quantity;
import com.mooninho.ordermanager.임시.domain.wrapper.order.TotalMenuPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsResponseDto {

    private MenuName menuName;
    private Quantity quantity;
    private TotalMenuPrice totalMenuPrice;
}
