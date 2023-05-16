package com.foodtech.mate.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalPrice {

    private final Integer totalPrice;

    private TotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static TotalPrice of(Integer totalPrice) {
        return new TotalPrice(totalPrice);
    }
}
