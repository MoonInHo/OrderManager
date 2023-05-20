package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.account.Name;
import com.foodtech.mate.domain.wrapper.account.Phone;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_company_id")
    private DeliveryCompany deliveryCompany;
    @OneToMany(mappedBy = "deliveryDriver")
    private List<Delivery> delivery = new ArrayList<>();
}