package com.order.manager.domain.entity;

import com.order.manager.domain.wrapper.menu.MenuName;
import com.order.manager.domain.wrapper.menu.Price;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private MenuName menuName;

    @Embedded
    private Price price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "menu")
    private List<OrderDetail> orderDetail = new ArrayList<>();

    private Menu(MenuName menuName, Price price, Store store) {
        this.menuName = menuName;
        this.price = price;
        this.store = store;
    }

    public static Menu createMenu(MenuName menuName, Price price, Store store) {
        return new Menu(menuName, price, store);
    }
}