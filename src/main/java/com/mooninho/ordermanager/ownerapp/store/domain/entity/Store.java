package com.mooninho.ordermanager.ownerapp.store.domain.entity;

import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.menu.domain.entity.Menu;
import com.mooninho.ordermanager.ownerapp.order.domain.entity.Order;
import com.mooninho.ordermanager.ownerapp.store.domain.vo.StoreName;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private StoreName storeName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menu = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<DeliveryCompany> deliveryCompanies = new ArrayList<>();

    private Store(Long id) {
        this.id = id;
    }

    private Store(StoreName storeName, Member member) {
        this.storeName = storeName;
        this.member = member;
    }

    public static Store createKeyObject(Long storeId) {
        return new Store(storeId);
    }

    public static Store createStore(StoreName storeName, Member owner) {
        return new Store(storeName, owner);
    }
}
