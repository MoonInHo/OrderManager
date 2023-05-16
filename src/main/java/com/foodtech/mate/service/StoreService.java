package com.foodtech.mate.service;

import com.foodtech.mate.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreQueryRepository storeQueryRepository;

    public Long findStoreId(Long userCode) {
        return storeQueryRepository.findStoreIdByUserCode(userCode);
    }
}
