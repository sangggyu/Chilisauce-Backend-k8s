package com.example.chillisaucek8s.domain.reservations.service;

import com.example.chillisaucek8s.domain.reservations.dto.ReservationUserWrapper;
import com.example.chillisaucek8s.domain.reservations.dto.response.*;
import com.example.chillisaucek8s.domain.reservations.entity.Reservation;
import com.example.chillisaucek8s.domain.reservations.respository.ReservationRepository;
import com.example.chillisaucek8s.domain.reservations.respository.ReservationUserRepository;
import com.example.chillisaucek8s.domain.spaces.entity.Mr;
import com.example.chillisaucek8s.domain.users.entity.User;
import com.example.chillisaucek8s.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationUserRepository reservationUserRepository;

    /**
     * 특정 유저의 예약 내역 조회
     */
    @Transactional(readOnly = true)
    public UserReservationListResponse getUserReservations(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        List<Reservation> reservations = reservationRepository.findAllByUserId(user.getId());
        List<Long> ids = reservations.stream().mapToLong(Reservation::getId).boxed().toList();

        List<ReservationUserWrapper> linkList = reservationUserRepository.findReservationUserByReservationIdIn(ids);

        return new UserReservationListResponse(reservations.stream().map(x -> {
            Mr m = x.getMeetingRoom();
            User u = x.getUser();
            Long mrId = m == null ? 0 : m.getId();
            String username = u == null ? "탈퇴한 유저" : user.getUsername();
            List<UsernameResponse> userList = linkList.stream().filter(y -> y.getReservationId().equals(x.getId()))
                    .map(UsernameResponse::new).toList();
            return new UserReservationResponse(x, mrId, username, userList);
        }).toList());
    }
}