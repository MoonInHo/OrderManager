package com.mooninho.ordermanager.ownerapp.orderhistory.domain.entity;

import com.mooninho.ordermanager.ownerapp.order.domain.entity.Order;
import com.mooninho.ordermanager.ownerapp.orderhistory.domain.vo.CancelReason;
import com.mooninho.ordermanager.ownerapp.order.domain.vo.Timestamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCancelHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_cancel_history_id")
    private Long Id;

    @Embedded
    private CancelReason cancelReason;

    @Embedded
    @Column(nullable = false, name = "order_cancel_timestamp")
    private Timestamp timestamp;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private OrderCancelHistory(CancelReason cancelReason, Order order) {
        this.cancelReason = cancelReason;
        this.order = order;
        this.timestamp = Timestamp.of();
    }

    public static OrderCancelHistory createOrderCancelHistory(CancelReason cancelReason, Order order) {
        return new OrderCancelHistory(cancelReason, order);
    }
}
