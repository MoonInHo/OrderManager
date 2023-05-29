package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.order.Quantity;
import com.foodtech.mate.domain.wrapper.order.TotalMenuPrice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Embedded
    private TotalMenuPrice totalMenuPrice;

    @Embedded
    private Quantity quantity;
}