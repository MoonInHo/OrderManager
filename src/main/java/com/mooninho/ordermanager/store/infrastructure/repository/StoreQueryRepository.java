package com.mooninho.ordermanager.store.infrastructure.repository;

import com.mooninho.ordermanager.store.domain.vo.StoreName;

public interface StoreQueryRepository {

    boolean isExistStoreName(StoreName storeName);

    boolean isNotOwner(Long storeId, Long memberId);
}
