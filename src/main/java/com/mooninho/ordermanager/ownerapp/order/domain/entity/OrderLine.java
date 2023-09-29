package com.mooninho.ordermanager.ownerapp.order.domain.entity;

import com.mooninho.ordermanager.ownerapp.menu.domain.entity.Menu;
import com.mooninho.ordermanager.ownerapp.order.domain.vo.Quantity;
import com.mooninho.ordermanager.ownerapp.order.domain.vo.TotalMenuPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Embedded
    @Column(nullable = false)
    private TotalMenuPrice totalMenuPrice;

    @Embedded
    @Column(nullable = false)
    private Quantity quantity;
}