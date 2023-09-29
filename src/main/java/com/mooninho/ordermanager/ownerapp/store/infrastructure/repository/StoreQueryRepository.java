package com.mooninho.ordermanager.ownerapp.store.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.store.domain.vo.StoreName;

public interface StoreQueryRepository {

    boolean isExistStoreName(StoreName storeName);

    boolean isNotOwner(Long storeId, Long memberId);

    boolean isOwner(Long storeId, Long ownerId);
}
