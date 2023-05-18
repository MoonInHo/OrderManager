package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.store.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;
    @Embedded
    private BusinessNumber businessNumber;
    @Embedded
    private StoreNumber storeNumber;
    @Embedded
    private StoreName storeName;
    @Embedded
    private RepresentativeName representativeName;
    @Embedded
    private Address address;
    @Embedded
    private StoreContact storeContact;
    @Embedded
    private BusinessType businessType;
    @Embedded
    private Industry industry;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @OneToMany(mappedBy = "store")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "store")
    private List<Menu> menu = new ArrayList<>();
//    @OneToMany(mappedBy = "store")
//    private List<SetMenu> setMenu = new ArrayList<>();
}