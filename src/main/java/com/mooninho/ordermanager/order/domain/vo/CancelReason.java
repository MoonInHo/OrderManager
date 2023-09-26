package com.mooninho.ordermanager.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CancelReason {

    private final String cancelReason;

    private CancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public static CancelReason of(String cancelReason) {
        return new CancelReason(cancelReason);
    }
}
