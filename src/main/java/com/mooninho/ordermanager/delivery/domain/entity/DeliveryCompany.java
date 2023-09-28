package com.mooninho.ordermanager.delivery.domain.entity;

import com.mooninho.ordermanager.delivery.domain.vo.Company;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_company_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Company company;

    @OneToMany(mappedBy = "deliveryCompany")
    private List<Delivery> delivery = new ArrayList<>();

    @OneToMany(mappedBy = "deliveryCompany")
    private List<DeliveryDriver> deliveryDriver = new ArrayList<>();

    private DeliveryCompany(Long id) {
        this.id = id;
    }

    public static DeliveryCompany createKeyObject(Long id) {
        return new DeliveryCompany(id);
    }
}
