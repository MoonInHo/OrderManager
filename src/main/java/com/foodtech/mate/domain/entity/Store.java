package com.foodtech.mate.domain.entity;

import com.foodtech.mate.domain.wrapper.store.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private AddressDetail addressDetail;
    @Embedded
    private StoreContact storeContact;
    @Embedded
    private BusinessType businessType;
    @Embedded
    private Industry industry;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Store(BusinessNumber businessNumber, StoreNumber storeNumber, StoreName storeName,
                  RepresentativeName representativeName, Address address, AddressDetail addressDetail,
                  StoreContact storeContact, BusinessType businessType, Industry industry)
    {
        this.businessNumber = businessNumber;
        this.storeNumber = storeNumber;
        this.storeName = storeName;
        this.representativeName = representativeName;
        this.address = address;
        this.addressDetail = addressDetail;
        this.storeContact = storeContact;
        this.businessType = businessType;
        this.industry = industry;
    }

    public static Store saveStoreInfo(
            BusinessNumber businessNumber, StoreNumber storeNumber,
            StoreName storeName, RepresentativeName representativeName,
            Address address, AddressDetail addressDetail,
            StoreContact storeContact, BusinessType businessType, Industry industry)
    {
        return new Store(businessNumber, storeNumber, storeName, representativeName,
                address, addressDetail, storeContact, businessType, industry);
    }
}