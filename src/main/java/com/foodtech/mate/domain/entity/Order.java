package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.foodtech.mate.domain.wrapper.order.OrderTimestamp;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private OrderTimestamp orderTimestamp;
    @Embedded
    private TotalPrice totalPrice;
    @Embedded
    private CustomerRequest customerRequest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetail = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private Delivery delivery;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderState orderState;
}