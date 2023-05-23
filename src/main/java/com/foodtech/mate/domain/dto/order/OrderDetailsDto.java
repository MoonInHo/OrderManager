package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.wrapper.menu.MenuName;
import com.foodtech.mate.domain.wrapper.menu.Price;
import com.foodtech.mate.domain.wrapper.order.Quantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    private MenuName menuName;
    private Quantity quantity;
    private Price price;
}
