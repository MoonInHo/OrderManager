package com.order.manager.domain.wrapper.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class OrderTimestamp {

    @Column(nullable = false)
    private final LocalDateTime orderTimestamp;

    private OrderTimestamp(String formattedDateTime) {
        this.orderTimestamp = LocalDateTime.parse(formattedDateTime);
    }

    //TODO 주문 생성 기능 생성시 타임포매터 적용하기
    public static OrderTimestamp of(LocalDateTime orderTimestamp) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        return new OrderTimestamp(formattedDateTime);
    }
}
