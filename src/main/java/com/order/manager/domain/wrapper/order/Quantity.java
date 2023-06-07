package com.order.manager.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Quantity {

    @Column(nullable = false)
    private final Integer quantity;

    private Quantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static Quantity of(Integer quantity) {

        if (quantity == null) {
            throw new IllegalArgumentException("수량을 입력하세요");
        }

        return new Quantity(quantity);
    }
}
