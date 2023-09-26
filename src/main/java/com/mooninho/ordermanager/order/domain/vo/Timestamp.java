package com.mooninho.ordermanager.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter // TODO Getter 필요성 체크 후 각 vo 에서 Getter 사용 최소화
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Timestamp {

    private final String timestamp;

    private Timestamp(String formattedTimestamp) {
        this.timestamp = formattedTimestamp;
    }

    public static Timestamp of() {

        LocalDateTime now = LocalDateTime.now();;

        return new Timestamp(now.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss")));
    }
}
