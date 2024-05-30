package com.mooninho.ordermanager.ownerapp.deliverycompany.application.service;

import com.mooninho.ordermanager.ownerapp.deliverycompany.application.dto.request.CreateDeliveryCompanyRequestDto;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.repository.DeliveryCompanyRepository;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo.CompanyName;
import com.mooninho.ordermanager.ownerapp.exception.exception.deliverycompany.DuplicateCompanyNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryCompanyService {

    private final DeliveryCompanyRepository deliveryCompanyRepository;

    @Transactional
    public void createDeliveryCompany(Long storeId, CreateDeliveryCompanyRequestDto createDeliveryCompanyRequestDto) {

        checkDuplicateCompanyName(createDeliveryCompanyRequestDto.getCompanyName()); // TODO 배달 업체 추가 정책 고민

        deliveryCompanyRepository.save(createDeliveryCompanyRequestDto.toEntity(storeId));
    }

    protected void checkDuplicateCompanyName(String companyName) {
        boolean companyNameExist = deliveryCompanyRepository.isCompanyNameExist(CompanyName.of(companyName));
        if (companyNameExist) {
            throw new DuplicateCompanyNameException();
        }
    }
}
