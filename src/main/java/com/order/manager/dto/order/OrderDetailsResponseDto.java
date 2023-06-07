package com.order.manager.dto.order;

import com.order.manager.domain.wrapper.menu.MenuName;
import com.order.manager.domain.wrapper.order.Quantity;
import com.order.manager.domain.wrapper.order.TotalMenuPrice;
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
