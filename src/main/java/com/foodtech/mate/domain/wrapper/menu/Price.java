package com.foodtech.mate.domain.wrapper.menu;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Price {

    private final String price;

    private Price(String price) {
        this.price = price;
    }

    public static Price of(Integer price) {

        if (price == null) {
            throw new IllegalArgumentException("가격을 입력해주세요");
        }

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
        String formattedPrice = format.format(price);

        return new Price(formattedPrice);
    }
}