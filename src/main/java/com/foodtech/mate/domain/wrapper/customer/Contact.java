package com.foodtech.mate.domain.wrapper.customer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Contact {

    private final String contact;

    private Contact(String contact) {
        this.contact = contact;
    }

    public static Contact of(String contact) {
        return new Contact(contact);
    }
}
