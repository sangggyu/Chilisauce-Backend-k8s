package com.example.chillisaucek8s.domain.users.dto.response;

import com.example.chillisaucek8s.domain.users.entity.User;
import com.example.chillisaucek8s.domain.users.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailResponseDto {
    Long userId;
    String email;
    String username;
    UserRoleEnum role;

    public UserDetailResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}