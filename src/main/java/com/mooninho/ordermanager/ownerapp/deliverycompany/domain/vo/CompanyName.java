package com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CompanyName {

    private final String companyName;

    private CompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static CompanyName of(String companyName) {

        if (companyName == null || companyName.isBlank()) {
            throw new IllegalArgumentException("배달 업체명을 입력하세요.");
        }

        return new CompanyName(companyName);
    }
}
