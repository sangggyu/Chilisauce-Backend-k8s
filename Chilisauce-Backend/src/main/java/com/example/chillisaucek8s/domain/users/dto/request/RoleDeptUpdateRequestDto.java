package com.example.chillisaucek8s.domain.users.dto.request;

import com.example.chillisaucek8s.domain.users.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDeptUpdateRequestDto {
    private UserRoleEnum role;
    private boolean updateRole;
}