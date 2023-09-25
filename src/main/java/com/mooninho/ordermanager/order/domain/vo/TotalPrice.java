package com.mooninho.ordermanager.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalPrice {

    private final String totalPrice;

    private TotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static TotalPrice of(Integer totalPrice) {

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

        return new TotalPrice(numberFormat.format(totalPrice));
    }
}
