package com.mooninho.ordermanager.store.application.service;

import com.mooninho.ordermanager.exception.exception.NotFoundUserException;
import com.mooninho.ordermanager.member.domain.repository.MemberRepository;
import com.mooninho.ordermanager.member.domain.vo.UserId;
import com.mooninho.ordermanager.store.application.dto.CreateStoreRequestDto;
import com.mooninho.ordermanager.store.domain.entity.Store;
import com.mooninho.ordermanager.store.domain.repository.StoreRepository;
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

        Store store = createStoreRequestDto.toEntity(getMemberId(userId));

        storeRepository.save(store);
    }

    @Transactional(readOnly = true)
    protected Long getMemberId(String userId) {
        return memberRepository.getMemberId(UserId.of(userId))
                .orElseThrow(NotFoundUserException::new);
    }
}
