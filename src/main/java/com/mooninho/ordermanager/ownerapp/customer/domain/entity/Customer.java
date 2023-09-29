package com.mooninho.ordermanager.ownerapp.customer.domain.entity;

import com.mooninho.ordermanager.ownerapp.order.domain.entity.Order;
import com.mooninho.ordermanager.ownerapp.customer.domain.vo.Address;
import com.mooninho.ordermanager.ownerapp.customer.domain.vo.Contact;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false)
    private Address address;

    @Embedded
    @Column(nullable = false, unique = true)
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