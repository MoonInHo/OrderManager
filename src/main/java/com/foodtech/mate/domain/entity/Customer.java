package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.customer.Address;
import com.foodtech.mate.domain.wrapper.customer.Contact;
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
}