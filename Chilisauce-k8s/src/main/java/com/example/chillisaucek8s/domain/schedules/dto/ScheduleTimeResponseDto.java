package com.example.chillisaucek8s.domain.schedules.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
@Getter
@NoArgsConstructor
@Builder
@Schema(description = "스케줄 타임테이블 시각 단위 응답 DTO")
public class ScheduleTimeResponseDto {
    @Schema(description = "해당 시각 예약 여부")
    Boolean isCheckOut;

    @Schema(description = "시작 시각")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    LocalTime start;

    @Schema(description = "종료 시각")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    LocalTime end;

    public ScheduleTimeResponseDto(boolean isCheckOut, LocalTime start, LocalTime end) {
        this.isCheckOut=isCheckOut;
        this.start=start;
        this.end=end;
    }
}