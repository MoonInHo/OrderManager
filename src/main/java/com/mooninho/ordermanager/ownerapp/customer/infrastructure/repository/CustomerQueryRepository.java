package com.mooninho.ordermanager.ownerapp.customer.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.customer.domain.vo.Contact;

public interface CustomerQueryRepository {

    boolean isExistContact(Contact contact);
}
