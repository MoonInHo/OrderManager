package com.order.manager.service.store;

import com.order.manager.repository.StoreQueryRepository;
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
