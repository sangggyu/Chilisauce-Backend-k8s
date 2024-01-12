package com.example.chillisaucek8s.domain.users.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminSignupResponseDto {
    private final String certification;

    public AdminSignupResponseDto(String certification) {
        this.certification = certification;
    }
}
