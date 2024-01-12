package com.example.chillisaucek8s.domain.reservations.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 예약 정보 타임테이블
 */
@Getter
@AllArgsConstructor
//@Schema(description = "예약 타임테이블 응답 DTO")
public class ReservationTimetableResponse {
//    @Schema(description = "회의실 Id")
    Long mrId;
//    @Schema(description = "회의실 이름")
    String mrName;
//    @Schema(description = "타임테이블 리스트")
    List<ReservationTimeResponse> timeList;
}