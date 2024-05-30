package com.mooninho.ordermanager.ownerapp.store.application.service;

import com.mooninho.ordermanager.ownerapp.exception.exception.store.DuplicateStoreNameException;
import com.mooninho.ordermanager.ownerapp.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;
import com.mooninho.ordermanager.ownerapp.store.application.dto.request.CreateStoreRequestDto;
import com.mooninho.ordermanager.ownerapp.store.domain.repository.StoreRepository;
import com.mooninho.ordermanager.ownerapp.store.domain.vo.StoreName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createStore(CreateStoreRequestDto createStoreRequestDto, String username) {

        checkDuplicateStoreName(createStoreRequestDto.getStoreName());

        storeRepository.save(createStoreRequestDto.toEntity(getOwnerId(username)));
    }

    protected void checkDuplicateStoreName(String storeName) {
        boolean existStoreName = storeRepository.isExistStoreName(StoreName.of(storeName));
        if (existStoreName) {
            throw new DuplicateStoreNameException();
        }
    }

    protected Long getOwnerId(String username) {
        return memberRepository.getMemberId(Username.of(username));
    }
}
