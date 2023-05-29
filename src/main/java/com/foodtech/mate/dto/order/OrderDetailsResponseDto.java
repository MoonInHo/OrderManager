package com.foodtech.mate.dto.order;

import com.foodtech.mate.domain.wrapper.menu.MenuName;
import com.foodtech.mate.domain.wrapper.menu.Price;
import com.foodtech.mate.domain.wrapper.order.Quantity;
import com.foodtech.mate.domain.wrapper.order.TotalMenuPrice;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
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
