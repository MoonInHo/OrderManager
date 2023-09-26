package com.mooninho.ordermanager.order.domain.entity;

import com.mooninho.ordermanager.menu.domain.entity.Menu;
import com.mooninho.ordermanager.order.domain.vo.Quantity;
import com.mooninho.ordermanager.order.domain.vo.TotalMenuPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail { // TODO OrderLine 과 OrderDetail 중 어떤게 더 적절한 이름인지 고민

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
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