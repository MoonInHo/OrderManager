package com.order.manager.domain.wrapper.customer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

import static com.order.manager.util.validation.PatternMatcher.isInvalidPhoneFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Contact {

    @Column(unique = true)
    private final String contact;

    private Contact(String contact) {
        this.contact = contact;
    }

    public static Contact of(String contact) {

        if (contact == null) {
            throw new IllegalArgumentException("! 연락처를 입력해 주세요.");
        }

        if (contact.isBlank()) {
            throw new IllegalArgumentException("! 공백을 사용할 수 없습니다.");
        }

        if (isInvalidPhoneFormat(contact)) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }

        return new Contact(contact);
    }
}
