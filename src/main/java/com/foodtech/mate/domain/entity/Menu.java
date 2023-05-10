package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.menu.MenuName;
import com.foodtech.mate.domain.wrapper.menu.Price;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private MenuName menuName;
    @Embedded
    private Price price;
}