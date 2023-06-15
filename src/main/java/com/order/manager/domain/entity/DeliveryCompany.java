package com.order.manager.domain.entity;

import com.order.manager.domain.wrapper.delivery.Company;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    public static DeliveryCompany createKeyValue(Long id) {
        return new DeliveryCompany(id);
    }
}
