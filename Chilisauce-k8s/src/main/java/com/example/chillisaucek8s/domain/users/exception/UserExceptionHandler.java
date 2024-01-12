package com.example.chillisaucek8s.domain.users.exception;

import com.example.chillisaucek8s.global.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    protected ResponseEntity<ResponseMessage<Object>> handleUserException(UserException e) {
        return ResponseMessage.responseError(e.getErrorCode());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ResponseMessage<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getFieldError() == null ? "" : e.getFieldError().getDefaultMessage();
        return ResponseMessage.responseError(message, HttpStatus.BAD_REQUEST);
    }

}