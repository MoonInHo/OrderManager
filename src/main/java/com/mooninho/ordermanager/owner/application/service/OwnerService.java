package com.mooninho.ordermanager.owner.application.service;

import com.mooninho.ordermanager.exception.exception.owner.DuplicatePhoneException;
import com.mooninho.ordermanager.exception.exception.owner.DuplicateUsernameException;
import com.mooninho.ordermanager.owner.application.dto.request.CreateOwnerRequestDto;
import com.mooninho.ordermanager.owner.domain.entity.Owner;
import com.mooninho.ordermanager.owner.domain.vo.Phone;
import com.mooninho.ordermanager.owner.domain.vo.Username;
import com.mooninho.ordermanager.owner.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final PasswordEncoder passwordEncoder;
    private final OwnerRepository ownerRepository;

    @Transactional
    public void signUp(CreateOwnerRequestDto createOwnerRequestDto) {

        checkDuplicateUsername(createOwnerRequestDto);
        checkDuplicatePhone(createOwnerRequestDto);

        Owner owner = createOwnerRequestDto.toEntity();
        owner.passwordEncrypt(passwordEncoder);

        ownerRepository.save(owner);
    }

    @Transactional(readOnly = true)
    protected void checkDuplicateUsername(CreateOwnerRequestDto createOwnerRequestDto) {

        boolean usernameExist = ownerRepository.isUsernameExist(Username.of(createOwnerRequestDto.getUsername()));
        if (usernameExist) {
            throw new DuplicateUsernameException();
        }
    }

    @Transactional(readOnly = true)
    protected void checkDuplicatePhone(CreateOwnerRequestDto createOwnerRequestDto) {

        boolean phoneExist = ownerRepository.isPhoneExist(Phone.of(createOwnerRequestDto.getPhone()));
        if (phoneExist) {
            throw new DuplicatePhoneException();
        }
    }
}
