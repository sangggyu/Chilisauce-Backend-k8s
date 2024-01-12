package com.example.chillisaucek8s.domain.schedules.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ScheduleException extends RuntimeException{
    private final String message;
    private final HttpStatus statusCode;

    public ScheduleException(ScheduleErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.statusCode = errorCode.getHttpStatus();
    }
}