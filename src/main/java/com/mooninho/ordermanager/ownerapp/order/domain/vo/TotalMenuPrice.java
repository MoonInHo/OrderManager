package com.mooninho.ordermanager.ownerapp.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalMenuPrice {

    private final String totalMenuPrice;

    private TotalMenuPrice(String totalMenuPrice) {
        this.totalMenuPrice = totalMenuPrice;
    }

    public static TotalMenuPrice of(Integer totalMenuPrice) {

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

        return new TotalMenuPrice(numberFormat.format(totalMenuPrice));
    }
}
