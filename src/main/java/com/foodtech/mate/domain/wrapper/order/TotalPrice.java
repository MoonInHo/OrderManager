package com.foodtech.mate.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TotalPrice {

    private final String totalPrice;

    private TotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

//    public static TotalPrice of(Integer totalPrice) {
//
////        NumberFormat format = NumberFormat.getInstance();
////        format.setGroupingUsed(true);
////        String formattedTotalPrice = format.format(totalPrice);
//
//        return new TotalPrice(formattedTotalPrice);
//    }
}
