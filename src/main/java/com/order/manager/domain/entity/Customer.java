package com.order.manager.domain.entity;

import com.order.manager.domain.wrapper.customer.Address;
import com.order.manager.domain.wrapper.customer.Contact;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> order = new ArrayList<>();

    private Customer(Address address, Contact contact) {
        this.address = address;
        this.contact = contact;
    }

    public static Customer createCustomer(Address address, Contact contact) {
        return new Customer(address, contact);
    }
}