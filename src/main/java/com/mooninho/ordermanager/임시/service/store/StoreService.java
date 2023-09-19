package com.mooninho.ordermanager.임시.service.store;

import com.mooninho.ordermanager.임시.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreQueryRepository storeQueryRepository;

    public Long findStore(Long userKey) {
        return storeQueryRepository.findStoreIdByUserKey(userKey);
    }
}
