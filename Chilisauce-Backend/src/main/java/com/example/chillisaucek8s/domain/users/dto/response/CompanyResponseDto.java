package com.example.chillisaucek8s.domain.users.dto.response;

import com.example.chillisaucek8s.domain.users.entity.Companies;
import lombok.Getter;

@Getter
public class CompanyResponseDto {
    private final String companyName;
    private final String certification;

    public CompanyResponseDto(Companies companies) {
        this.companyName = companies.getCompanyName();
        this.certification = companies.getCertification();
    }
}