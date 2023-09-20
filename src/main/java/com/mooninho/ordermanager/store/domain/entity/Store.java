package com.mooninho.ordermanager.store.domain.entity;

import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.임시.domain.entity.Menu;
import com.mooninho.ordermanager.임시.domain.entity.Order;
import com.mooninho.ordermanager.store.domain.vo.StoreName;
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
    private StoreName storeName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menu = new ArrayList<>();

    private Store(Long id) {
        this.id = id;
    }

    private Store(StoreName storeName, Member member) {
        this.storeName = storeName;
        this.member = member;
    }

    public static Store createKeyValue(Long storeId) {
        return new Store(storeId);
    }

    public static Store createStore(StoreName storeName, Member member) {
        return new Store(storeName, member);
    }
}
