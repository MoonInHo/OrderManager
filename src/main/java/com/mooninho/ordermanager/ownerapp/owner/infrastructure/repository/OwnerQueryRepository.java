package com.mooninho.ordermanager.ownerapp.owner.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.owner.domain.entity.Owner;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Phone;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Username;

import java.util.Optional;

public interface OwnerQueryRepository {

    boolean isUsernameExist(Username username);

    boolean isPhoneExist(Phone phone);

    Optional<Owner> findByUsername(Username username);

    Optional<Long> getOwnerId(Username username);
}
