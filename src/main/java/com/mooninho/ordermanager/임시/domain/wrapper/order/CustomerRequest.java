package com.mooninho.ordermanager.임시.domain.wrapper.order;

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
        return new CustomerRequest(customerRequest);
    }
}
