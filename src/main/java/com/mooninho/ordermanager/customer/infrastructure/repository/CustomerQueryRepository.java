package com.mooninho.ordermanager.customer.infrastructure.repository;

import com.mooninho.ordermanager.customer.domain.vo.Contact;

public interface CustomerQueryRepository {

    boolean isExistContact(Contact contact);
}
