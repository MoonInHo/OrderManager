package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.state.DeliveryState;
import com.foodtech.mate.domain.wrapper.delivery.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_driver_id")
    private DeliveryDriver deliveryDriver;
    @Embedded
    private DeliveryTips deliveryTips;
    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;

    private Delivery(Order order, DeliveryTips deliveryTips) {
        this.order = order;
        this.deliveryTips = deliveryTips;
        this.deliveryState = DeliveryState.WAITING;
    }

    public static Delivery createDeliveryInfo(Order order, DeliveryTips deliveryTips) {
        return new Delivery(order, deliveryTips);
    }
}