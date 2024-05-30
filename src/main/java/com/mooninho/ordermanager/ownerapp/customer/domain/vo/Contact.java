package com.mooninho.ordermanager.ownerapp.customer.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Contact {

    private final String contact;

    private Contact(String contact) {
        this.contact = contact;
    }

    public static Contact of(String contact) {

        if (contact == null || contact.isBlank()) {
            throw new IllegalArgumentException("고객 연락처를 입력하세요.");
        }
        if (!contact.matches("^010-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new IllegalArgumentException("고객 연락처 형식이 올바르지 않습니다.");
        }
        return new Contact(contact);
    }
}
