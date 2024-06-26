package com.mooninho.ordermanager.ownerapp.store.application.dto.request;

import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import com.mooninho.ordermanager.ownerapp.store.domain.vo.StoreName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreRequestDto {

    private String storeName;

    public Store toEntity(Long memberId) {
        return Store.createStore(
                StoreName.of(storeName),
                Member.createKeyObject(memberId)
        );
    }
}
