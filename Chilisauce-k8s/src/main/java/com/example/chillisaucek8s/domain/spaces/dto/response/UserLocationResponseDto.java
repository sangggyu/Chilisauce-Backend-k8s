package com.example.chillisaucek8s.domain.spaces.dto.response;

import com.example.chillisaucek8s.domain.spaces.entity.UserLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLocationResponseDto {

    private String username;

    public UserLocationResponseDto(UserLocation userLocation) {
        this.username = userLocation.getUsername();
    }
}