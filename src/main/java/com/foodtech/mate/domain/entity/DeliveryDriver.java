package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.account.Name;
import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_driver_id")
    private Long id;
    @Embedded
    private Name driverName;
    @Embedded
    private Phone dirverPhone;
    @Enumerated(EnumType.STRING)
    private Company company;
    @OneToMany(mappedBy = "deliveryDriver")
    private List<Delivery> delivery = new ArrayList<>();
}
