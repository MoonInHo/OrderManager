package com.foodtech.mate.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderTimestamp {

    private final LocalDateTime orderTimeStamp;

    private OrderTimestamp(String formattedDateTime) {
        this.orderTimeStamp = LocalDateTime.parse(formattedDateTime);
    }

//    public static OrderTimestamp of() {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        String formattedDateTime = now.format(formatter);

//        return new OrderTimestamp(formattedDateTime);
//    }
}
