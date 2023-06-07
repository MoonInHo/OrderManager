package com.order.manager.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalMenuPrice {

    @Column(nullable = false)
    private final Integer totalMenuPrice;

    private TotalMenuPrice(Integer totalMenuPrice) {
        this.totalMenuPrice = totalMenuPrice;
    }

    public static TotalMenuPrice of(Integer totalMenuPrice) {

        return new TotalMenuPrice(totalMenuPrice);
    }
}
