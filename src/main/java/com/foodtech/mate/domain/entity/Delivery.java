package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.delivery.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @Enumerated(EnumType.STRING)
    private Company company;
    @Embedded
    private DriverName driverName;
    @Embedded
    private DriverPhone driverPhone;
    @Embedded
    private DeliveryTips deliveryTips;
    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;
}