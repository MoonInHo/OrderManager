package com.mooninho.ordermanager.임시.domain.wrapper.order;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
