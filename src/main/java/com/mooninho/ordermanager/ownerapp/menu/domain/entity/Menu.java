package com.mooninho.ordermanager.ownerapp.menu.domain.entity;

import com.mooninho.ordermanager.ownerapp.menu.domain.vo.MenuName;
import com.mooninho.ordermanager.ownerapp.menu.domain.vo.Price;
import com.mooninho.ordermanager.ownerapp.order.domain.entity.OrderLine;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private MenuName menuName;

    @Embedded
    @Column(nullable = false)
    private Price price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "menu")
    private List<OrderLine> orderLines = new ArrayList<>();

    private Menu(MenuName menuName, Price price, Store store) {
        this.menuName = menuName;
        this.price = price;
        this.store = store;
    }

    public static Menu createMenu(MenuName menuName, Price price, Store store) {
        return new Menu(menuName, price, store);
    }
}