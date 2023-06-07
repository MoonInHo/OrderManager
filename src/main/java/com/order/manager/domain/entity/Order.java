package com.order.manager.domain.entity;

import com.order.manager.domain.wrapper.order.CustomerRequest;
import com.order.manager.domain.wrapper.order.OrderTimestamp;
import com.order.manager.domain.wrapper.order.TotalPrice;
import com.order.manager.enums.state.OrderState;
import com.order.manager.enums.type.OrderType;
import com.order.manager.enums.type.PaymentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Embedded
    private OrderTimestamp orderTimestamp;

    @Embedded
    private TotalPrice totalPrice;

    @Embedded
    private CustomerRequest customerRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail = new ArrayList<>();

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
}