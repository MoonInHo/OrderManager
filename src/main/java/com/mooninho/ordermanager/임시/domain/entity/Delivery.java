package com.mooninho.ordermanager.임시.domain.entity;

import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DeliveryTips;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_company_id")
    private DeliveryCompany deliveryCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_driver_id")
    private DeliveryDriver deliveryDriver;

    @Embedded
    private DeliveryTips deliveryTips;

    @Enumerated(EnumType.STRING)
    private DeliveryState deliveryState;

    private Delivery(Order order, DeliveryCompany deliveryCompany, DeliveryTips deliveryTips) {
        this.order = order;
        this.deliveryCompany = deliveryCompany;
        this.deliveryTips = deliveryTips;
        this.deliveryState = DeliveryState.WAITING;
    }

    public static Delivery createDeliveryInfo(Order order, DeliveryCompany deliveryCompany, DeliveryTips deliveryTips) {
        return new Delivery(order, deliveryCompany, deliveryTips);
    }
}