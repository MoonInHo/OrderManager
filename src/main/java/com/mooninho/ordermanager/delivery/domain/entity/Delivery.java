package com.mooninho.ordermanager.delivery.domain.entity;

import com.mooninho.ordermanager.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.order.domain.entity.Order;
import com.mooninho.ordermanager.delivery.domain.vo.DeliveryTips;
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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "order_id",
            unique = true,
            nullable = false
    )
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_company_id", nullable = false)
    private DeliveryCompany deliveryCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_driver_id")
    private DeliveryDriver deliveryDriver;

    @Embedded
    private DeliveryTips deliveryTips;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Delivery(Order order, DeliveryCompany deliveryCompany, DeliveryTips deliveryTips) {
        this.order = order;
        this.deliveryCompany = deliveryCompany;
        this.deliveryTips = deliveryTips;
        this.deliveryStatus = DeliveryStatus.WAITING;
    }

    public static Delivery createDelivery(Order order, DeliveryCompany deliveryCompany, DeliveryTips deliveryTips) {
        return new Delivery(order, deliveryCompany, deliveryTips);
    }
}