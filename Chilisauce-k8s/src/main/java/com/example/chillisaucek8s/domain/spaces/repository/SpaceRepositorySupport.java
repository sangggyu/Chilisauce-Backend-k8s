package com.example.chillisaucek8s.domain.spaces.repository;

import com.example.chillisaucek8s.domain.spaces.dto.response.SpaceListResponseDto;
import com.example.chillisaucek8s.domain.spaces.dto.response.SpaceResponseDto;

import java.util.List;

public interface SpaceRepositorySupport {
    List<SpaceResponseDto> getSpacesList(Long spaceId);
    List<SpaceListResponseDto> getSpaceAllList(String companyName);
    void clearAllReservationsForSpace(Long spaceId);


}