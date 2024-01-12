package com.example.chillisaucek8s.domain.spaces.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FloorRequestDto {

    private String floorName;
    public FloorRequestDto(String floorName) {
        this.floorName = floorName;
    }

}