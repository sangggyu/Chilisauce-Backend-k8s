package com.example.chillisaucek8s.domain.reservations.respository;

import com.example.chillisaucek8s.domain.reservations.entity.Reservation;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r " +
            "left join fetch r.user " +
            "left join fetch r.meetingRoom " +
            "where r.user.id=:userId")
    List<Reservation> findAllByUserId(@Param("userId") Long userId);

    @Query(value = "select r " +
            "from Reservation r " +
            "left join fetch r.user as ru " +
            "left join fetch ru.companies as rc "+
            "left join fetch r.meetingRoom as rm " +
            "where rc.companyName = :companyName",
            countQuery="select count(r) from Reservation r")
    Page<Reservation> findAllByCompanyName(@Param("companyName") String companyName, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Reservation r " +
            "where r.meetingRoom.id = :meetingRoomId and r.startTime < :endTime and r.endTime > :startTime")
    Optional<Reservation> findFirstByMeetingRoomIdAndStartTimeLessThanAndEndTimeGreaterThan(
            @Param("meetingRoomId") Long meetingRoomId,
            @Param("startTime") LocalDateTime start,
            @Param("endTime") LocalDateTime end);

    @Query("select r from Reservation r " +
            "where r.meetingRoom.id = :meetingRoomId and r.startTime between :startTime and :endTime")
    List<Reservation> findAllByMeetingRoomIdAndStartTimeBetween(
            @Param("meetingRoomId") Long meetingRoomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    @Query("select r from Reservation r " +
            "where r.meetingRoom.id = :mrId and r.id != :reservationId " +
            "and r.startTime < :endTime and r.endTime > :startTime")
    List<Reservation> findAllByMeetingRoomIdAndIdNotAndStartTimeLessThanAndEndTimeGreaterThan(
            @Param("mrId") Long mrId,
            @Param("reservationId") Long reservationId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    List<Reservation> findAllByMeetingRoomId(Long mrId);
}