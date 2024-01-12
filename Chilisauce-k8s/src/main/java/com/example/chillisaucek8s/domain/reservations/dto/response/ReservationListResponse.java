package com.example.chillisaucek8s.domain.reservations.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationListResponse {
    List<ReservationDetailResponse> reservationList;
}