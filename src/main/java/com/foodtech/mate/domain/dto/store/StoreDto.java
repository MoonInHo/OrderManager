package com.foodtech.mate.domain.dto.store;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreDto {

    private Integer businessNumber;
    private Integer storeNumber;
    private String storeName;
    private String representativeName;
    private String storeAddress;
    private String storeAddressDetail;
    private String storeContact;
    private String businessType;
    private String industry;
}
