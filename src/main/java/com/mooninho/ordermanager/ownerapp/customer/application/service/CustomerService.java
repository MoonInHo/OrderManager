package com.mooninho.ordermanager.ownerapp.customer.application.service;

import com.mooninho.ordermanager.ownerapp.customer.application.dto.CreateCustomerRequestDto;
import com.mooninho.ordermanager.ownerapp.customer.domain.repository.CustomerRepository;
import com.mooninho.ordermanager.ownerapp.customer.domain.vo.Contact;
import com.mooninho.ordermanager.ownerapp.exception.exception.customer.DuplicateContactException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void createCustomer(CreateCustomerRequestDto createCustomerRequestDto) {

        checkDuplicateContact(createCustomerRequestDto);

        customerRepository.save(createCustomerRequestDto.toEntity());
    }

    protected void checkDuplicateContact(CreateCustomerRequestDto createCustomerRequestDto) {
        boolean existContact = customerRepository.isExistContact(Contact.of(createCustomerRequestDto.getContact()));
        if (existContact) {
            throw new DuplicateContactException();
        }
    }
}
