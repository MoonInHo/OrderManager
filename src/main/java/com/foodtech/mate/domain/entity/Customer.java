package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.customer.Contact;
import com.foodtech.mate.domain.wrapper.store.Address;
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
    private Long id;
    @Embedded
    private Address customerAddress;
    @Embedded
    private Contact customerContact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> order = new ArrayList<>();
}