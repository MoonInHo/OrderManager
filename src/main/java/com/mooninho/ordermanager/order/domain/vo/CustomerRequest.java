package com.mooninho.ordermanager.order.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CustomerRequest {

    private final String customerRequest;

    private CustomerRequest(String customerRequest) {
        this.customerRequest = customerRequest;
    }

    public static CustomerRequest of(String customerRequest) {

        if (customerRequest.length() < 50) {
            throw new IllegalArgumentException("50글자 이내로 입력해주세요.");
        }

        return new CustomerRequest(customerRequest);
    }
}
