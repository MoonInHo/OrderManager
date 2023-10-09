package com.mooninho.ordermanager.ownerapp.order.domain.entity;

import com.mooninho.ordermanager.ownerapp.customer.domain.entity.Customer;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.DeliveryAppType;
import com.mooninho.ordermanager.ownerapp.order.domain.vo.CustomerRequest;
import com.mooninho.ordermanager.ownerapp.order.domain.vo.Timestamp;
import com.mooninho.ordermanager.ownerapp.order.domain.vo.TotalPrice;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderType;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import com.mooninho.ordermanager.ownerapp.delivery.domain.entity.Delivery;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Embedded
    @Column(nullable = false)
    private Timestamp timestamp;

    @Embedded
    @Column(nullable = false)
    private TotalPrice totalPrice;

    @Embedded
    private CustomerRequest customerRequest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryAppType deliveryAppType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType orderType;

    private Order(Long id) {
        this.id = id;
    }

    private Order(
            TotalPrice totalPrice,
            CustomerRequest customerRequest,
            Store store,
            Customer customer,
            DeliveryAppType deliveryAppType,
            OrderStatus orderStatus,
            PaymentType paymentType,
            OrderType orderType
    ) {
        this.timestamp = Timestamp.of();
        this.totalPrice = totalPrice;
        this.customerRequest = customerRequest;
        this.store = store;
        this.customer = customer;
        this.deliveryAppType = deliveryAppType;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.orderType = orderType;
    }

    public static Order createKeyObject(Long id) {
        return new Order(id);
    }

    public static Order createOrder(
            TotalPrice totalPrice,
            CustomerRequest customerRequest,
            Store store,
            Customer customer,
            DeliveryAppType deliveryAppType,
            OrderStatus orderStatus,
            PaymentType paymentType,
            OrderType orderType
    ) {
        return new Order(
                totalPrice,
                customerRequest,
                store,
                customer,
                deliveryAppType,
                orderStatus,
                paymentType,
                orderType
        );
    }
}