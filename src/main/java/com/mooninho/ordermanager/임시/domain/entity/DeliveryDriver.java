package com.mooninho.ordermanager.임시.domain.entity;

import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DriverName;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DriverPhone;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    private DeliveryDriver(DriverName driverName, DriverPhone driverPhone, DeliveryCompany deliveryCompany) {
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.deliveryCompany = deliveryCompany;
    }

    public static DeliveryDriver createDeilveryDriver(DriverName driverName, DriverPhone driverPhone, DeliveryCompany deliveryCompany) {
        return new DeliveryDriver(driverName, driverPhone, deliveryCompany);
    }
}
