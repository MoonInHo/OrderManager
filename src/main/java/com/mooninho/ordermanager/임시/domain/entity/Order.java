package com.mooninho.ordermanager.임시.domain.entity;

import com.mooninho.ordermanager.임시.domain.wrapper.order.CustomerRequest;
import com.mooninho.ordermanager.임시.domain.wrapper.order.OrderTimestamp;
import com.mooninho.ordermanager.임시.domain.wrapper.order.TotalPrice;
import com.mooninho.ordermanager.임시.enums.state.OrderState;
import com.mooninho.ordermanager.임시.enums.type.OrderType;
import com.mooninho.ordermanager.임시.enums.type.PaymentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail = new ArrayList<>();

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Order(Long id) {
        this.id = id;
    }

    public static Order createKeyValue(Long id) {
        return new Order(id);
    }
}