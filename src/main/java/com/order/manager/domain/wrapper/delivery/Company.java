package com.order.manager.domain.wrapper.delivery;

import java.util.Arrays;

public enum Company {

    BAEMIN("배민"),
    BAEMINONE("배민원"),
    BAROGO("바로고"),
    VROONG("부릉");

    private String CompanyName;

    Company(String companyName) {
        CompanyName = companyName;
    }

    public static Company findByCompanyName(String CompanyName){

        return Arrays.stream(Company.values())
                .filter(company -> company.CompanyName.equals(CompanyName))
                .findAny()
                .orElse(null);
    }
}
