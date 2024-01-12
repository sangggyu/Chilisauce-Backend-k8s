package com.example.chillisaucek8s.domain.reservations.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 예약 시 시간 단위
 */
@Getter
@AllArgsConstructor
public class TimeUnit {
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime start;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime end;
}