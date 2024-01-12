package com.example.chillisaucek8s.domain.spaces.exception;

import com.example.chillisaucek8s.global.message.ErrorStatusMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum SpaceErrorCode implements ErrorStatusMessage {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    BOX_ALREADY_IN_USER(BAD_REQUEST, "이미 사용중인 사용자가 있습니다."),
    NOT_HAVE_PERMISSION(BAD_REQUEST, "권한이 없습니다."),
    USER_ALREADY_AT_LOCATION(BAD_REQUEST, "이미 사용중인 자리입니다."),
    SPACE_DOES_NOT_BELONG_TO_COMPANY (BAD_REQUEST, "회사에 해당 공간이 존재하지 않습니다."),
    NOT_HAVE_PERMISSION_COMPANIES(BAD_REQUEST, "해당 회사에 대한 권한이 없습니다."),

    //    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */

    SPACE_NOT_FOUND(NOT_FOUND, "해당 공간을 찾을 수 없습니다."),
    LOCATION_NOT_FOUND(NOT_FOUND, "해당 위치를 찾을 수 없습니다."),
    BOX_NOT_FOUND(NOT_FOUND, "해당 자리를 찾을 수 없습니다."),
    MR_NOT_FOUND(NOT_FOUND, "해당 회의실을 찾을 수 없습니다."),
    COMPANIES_NOT_FOUND(NOT_FOUND,"해당 회사가 존재하지 않습니다."),

    USER_NOT_FOUND(NOT_FOUND, "등록된 사용자가 없습니다"),
    FLOOR_NOT_FOUND(NOT_FOUND, "해당 FLOOR 를 찾을 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}