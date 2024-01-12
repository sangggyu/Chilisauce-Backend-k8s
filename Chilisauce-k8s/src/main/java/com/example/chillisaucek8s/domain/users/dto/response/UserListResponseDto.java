package com.example.chillisaucek8s.domain.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponseDto {
    List<UserDetailResponseDto> userList;
}