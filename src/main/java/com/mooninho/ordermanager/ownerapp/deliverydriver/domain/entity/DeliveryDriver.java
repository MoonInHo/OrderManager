package com.mooninho.ordermanager.ownerapp.deliverydriver.domain.entity;

import com.mooninho.ordermanager.ownerapp.delivery.domain.entity.Delivery;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.ownerapp.deliverydriver.domain.vo.DriverName;
import com.mooninho.ordermanager.ownerapp.deliverydriver.domain.vo.DriverPhone;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
    private DriverName driverName;

    @Embedded
    private DriverPhone driverPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_company_id")
    private DeliveryCompany deliveryCompany;

    @OneToMany(mappedBy = "deliveryDriver")
    private List<Delivery> delivery = new ArrayList<>();
}
