package com.example.chillisaucek8s.domain.reservations.dto.response;

import com.example.chillisaucek8s.domain.reservations.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "유저 예약 정보 응답 DTO")
public class UserReservationResponse {
    @Schema(description = "예약 id")
    Long reservationId;
    @Schema(description = "회의실 id")
    Long mrId;
    @Schema(description = "회의실 이름")
    String mrName;
    @Schema(description = "예약한 직원 이름")
    String username;

    @Schema(description = "시작 시각")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    LocalDateTime start;

    @Schema(description = "종료 시각")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    LocalDateTime end;

    @Schema(description = "참석자 목록")
    List<UsernameResponse> userList;

    public UserReservationResponse(Reservation reservation, Long mrId, String username, List<UsernameResponse> userList) {
        this.reservationId = reservation.getId();
        this.mrId = mrId;
        this.mrName = reservation.getMeetingRoom() == null ? "삭제된 회의실" : reservation.getMeetingRoom().getLocationName();
        this.username = username;
        this.start = reservation.getStartTime();
        this.end = reservation.getEndTime();
        this.userList = userList;
    }
}