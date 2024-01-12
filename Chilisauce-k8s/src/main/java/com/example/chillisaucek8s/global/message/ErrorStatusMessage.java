package com.example.chillisaucek8s.global.message;

import org.springframework.http.HttpStatus;

public interface ErrorStatusMessage {
    HttpStatus getHttpStatus();
    String getMessage();
}