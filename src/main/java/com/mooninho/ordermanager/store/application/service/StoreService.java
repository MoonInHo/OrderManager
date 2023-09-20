package com.mooninho.ordermanager.store.application.service;

import com.mooninho.ordermanager.exception.exception.member.UserNotFoundException;
import com.mooninho.ordermanager.exception.exception.store.DuplicateStoreNameException;
import com.mooninho.ordermanager.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.member.domain.vo.UserId;
import com.mooninho.ordermanager.store.application.dto.request.CreateStoreRequestDto;
import com.mooninho.ordermanager.store.domain.repository.StoreRepository;
import com.mooninho.ordermanager.store.domain.vo.StoreName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createStore(CreateStoreRequestDto createStoreRequestDto, String userId) {

        checkDuplicateStoreName(createStoreRequestDto.getStoreName());

        storeRepository.save(createStoreRequestDto.toEntity(getMemberId(userId)));
    }

    @Transactional(readOnly = true)
    protected void checkDuplicateStoreName(String storeName) {

        boolean existStoreName = storeRepository.isExistStoreName(StoreName.of(storeName));
        if (existStoreName) {
            throw new DuplicateStoreNameException();
        }
    }

    @Transactional(readOnly = true)
    protected Long getMemberId(String userId) {

        return memberRepository.getMemberId(UserId.of(userId))
                .orElseThrow(UserNotFoundException::new);
    }
}
