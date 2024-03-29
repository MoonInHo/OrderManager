package com.mooninho.ordermanager.ownerapp.customer.application.dto;

import com.mooninho.ordermanager.ownerapp.customer.domain.entity.Customer;
import com.mooninho.ordermanager.ownerapp.customer.domain.vo.Address;
import com.mooninho.ordermanager.ownerapp.customer.domain.vo.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequestDto {

    private String address;
    private String addressDetail;
    private String contact;

    public Customer toEntity() {
        return Customer.createCustomer(
                Address.of(address, addressDetail),
                Contact.of(contact)
        );
    }
}
