package com.order.manager.enums.type;

import java.util.Arrays;

public enum PaymentType {

    PREPAYMENT("선결제"),
    CARD("카드"),
    CASH("현금");

    private String paymentTypeCode;

    PaymentType(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public static PaymentType findByPaymentType(String paymentTypeCode){

        return Arrays.stream(PaymentType.values())
                .filter(paymentType -> paymentType.paymentTypeCode.equals(paymentTypeCode))
                .findAny()
                .orElse(null);
    }

}
