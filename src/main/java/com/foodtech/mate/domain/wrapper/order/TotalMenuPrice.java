package com.foodtech.mate.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalMenuPrice {

    private final String totalMenuPrice;

    private TotalMenuPrice(String totalMenuPrice) {
        this.totalMenuPrice = totalMenuPrice;
    }

    public static TotalMenuPrice of(Integer totalMenuPrice) {

        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
        String formattedTotalMenuPrice = format.format(totalMenuPrice);

        return new TotalMenuPrice(formattedTotalMenuPrice);
    }
}
