package com.foodtech.mate.domain.dto.store;

import com.foodtech.mate.domain.entity.Store;
import com.foodtech.mate.domain.wrapper.store.*;
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

    public static StoreDto createStoreDto(
            Integer businessNumber, Integer storeNumber,
            String storeName, String representativeName,
            String storeAddress, String storeAddressDetail,
            String storeContact, String businessType, String industry
    )
    {
        return new StoreDto(
                businessNumber, storeNumber, storeName, representativeName,
                storeAddress, storeAddressDetail, storeContact, businessType, industry
        );
    }

    public static Store createStoreInfo(StoreDto storeDto)
    {
        return Store.saveStoreInfo(
                BusinessNumber.of(storeDto.getBusinessNumber()), StoreNumber.of(storeDto.getStoreNumber()),
                StoreName.of(storeDto.getStoreName()), RepresentativeName.of(storeDto.getRepresentativeName()),
                Address.of(storeDto.getStoreAddress()), AddressDetail.of(storeDto.getStoreAddressDetail()),
                StoreContact.of(storeDto.getStoreContact()), BusinessType.of(storeDto.getBusinessType()), Industry.of(storeDto.getIndustry())
        );
    }
}
