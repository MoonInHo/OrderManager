package com.mooninho.ordermanager.임시.domain.entity;

import com.mooninho.ordermanager.menu.domain.entity.Menu;
import com.mooninho.ordermanager.임시.domain.wrapper.order.Quantity;
import com.mooninho.ordermanager.임시.domain.wrapper.order.TotalMenuPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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