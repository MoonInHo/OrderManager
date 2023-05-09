package com.foodtech.mate.service;

import com.foodtech.mate.domain.entity.Store;
import com.foodtech.mate.repository.StoreQueryRepository;
import com.foodtech.mate.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreQueryRepository storeQueryRepository;

    @Transactional
    public void saveStoreInfo(Store storeInfo) {

        boolean userIdExist = storeQueryRepository.isBusinessNumberExist(storeInfo.getBusinessNumber());
        if (userIdExist) {
            throw new IllegalStateException("! 이미 등록된 사업자등록번호입니다.");
        }
        storeRepository.save(storeInfo);
    }
}
