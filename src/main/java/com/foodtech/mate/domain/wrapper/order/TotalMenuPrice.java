package com.foodtech.mate.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.text.NumberFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalMenuPrice {

    private final Integer totalMenuPrice;

    private TotalMenuPrice(Integer totalMenuPrice) {
        this.totalMenuPrice = totalMenuPrice;
    }

    public static TotalMenuPrice of(Integer totalMenuPrice) {

        return new TotalMenuPrice(totalMenuPrice);
    }
}
