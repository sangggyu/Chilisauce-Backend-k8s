package com.example.chillisaucek8s.domain.spaces.repository;

import com.example.chillisaucek8s.domain.spaces.dto.response.FloorResponseDto;

import java.util.List;

public interface FloorRepositorySupport {

    List<FloorResponseDto> getFloorAllList(String companyName);

    void clearAllReservationsForFloor(Long floorId);
}