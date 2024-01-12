package com.example.chillisaucek8s.domain.schedules.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTimetableResponseDto {
    @Schema(description = "타임테이블 리스트")
    List<ScheduleTimeResponseDto> timeList;
}