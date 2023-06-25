package com.order.manager.dto.customer;

import com.order.manager.domain.entity.Customer;
import com.order.manager.domain.wrapper.customer.Address;
import com.order.manager.domain.wrapper.customer.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String address;
    private String addressDetail;
    private String contact;

    public Customer toEntity() {
        return Customer.createCustomer(Address.of(address, addressDetail), Contact.of(contact));
    }
}
