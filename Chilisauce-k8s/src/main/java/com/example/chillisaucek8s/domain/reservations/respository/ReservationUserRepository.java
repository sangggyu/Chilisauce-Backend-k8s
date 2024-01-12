package com.example.chillisaucek8s.domain.reservations.respository;

import com.example.chillisaucek8s.domain.reservations.dto.ReservationUserWrapper;
import com.example.chillisaucek8s.domain.reservations.entity.ReservationUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationUserRepository extends JpaRepository<ReservationUser, Long> {
    @Query("select r.attendee.username as username, r.reservation.id as reservationId " +
            "from ReservationUser r " +
            "left join r.attendee " +
            "left join r.reservation where r.reservation.id in :reservationId")
    List<ReservationUserWrapper> findReservationUserByReservationIdIn(@Param("reservationId") List<Long> reservationId);
}