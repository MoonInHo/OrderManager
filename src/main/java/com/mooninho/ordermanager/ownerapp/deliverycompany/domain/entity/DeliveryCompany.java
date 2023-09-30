package com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity;

import com.mooninho.ordermanager.ownerapp.delivery.domain.entity.Delivery;
import com.mooninho.ordermanager.ownerapp.delivery.domain.entity.DeliveryDriver;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo.CompanyName;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Embedded
    @Column(nullable = false, unique = true)
    private CompanyName companyName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "deliveryCompany")
    private List<Delivery> delivery = new ArrayList<>();

    @OneToMany(mappedBy = "deliveryCompany")
    private List<DeliveryDriver> deliveryDriver = new ArrayList<>();

    private DeliveryCompany(Long id) {
        this.id = id;
    }

    private DeliveryCompany(CompanyName companyName, Store store) {
        this.companyName = companyName;
        this.store = store;
    }

    public static DeliveryCompany createKeyObject(Long id) {
        return new DeliveryCompany(id);
    }

    public static DeliveryCompany createDeliveryCompany(CompanyName companyName, Store store) {
        return new DeliveryCompany(companyName, store);
    }
}
