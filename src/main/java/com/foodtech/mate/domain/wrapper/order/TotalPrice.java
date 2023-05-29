package com.foodtech.mate.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalPrice {

    @Column(nullable = false)
    private final Integer totalPrice;

    public TotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static TotalPrice of(Integer totalPrice) {
        return new TotalPrice(totalPrice);
    }
}
