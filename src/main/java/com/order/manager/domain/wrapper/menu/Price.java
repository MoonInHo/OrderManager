package com.order.manager.domain.wrapper.menu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Price {

    private final Integer price;

    private Price(Integer price) {
        this.price = price;
    }

    public static Price of(Integer price) {

        if (price == null) {
            throw new IllegalArgumentException("가격을 입력해주세요");
        }
        return new Price(price);
    }
}