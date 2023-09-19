package com.mooninho.ordermanager.임시.domain.entity;

import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.임시.domain.wrapper.store.StoreName;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Menu> menu = new ArrayList<>();

    private Store(Long id) {
        this.id = id;
    }

    public static Store createKeyValue(Long storeId) {
        return new Store(storeId);
    }
}
