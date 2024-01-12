package com.example.chillisaucek8s.domain.users.dto.response;

import com.example.chillisaucek8s.domain.users.entity.Companies;
import com.example.chillisaucek8s.domain.users.entity.User;
import com.example.chillisaucek8s.domain.users.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long id;
    private final String email;
    private final String username;
    private final UserRoleEnum role;
    private final Companies companies;

    public LoginResponseDto(User user, UserRoleEnum role) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = role;
        this.companies = user.getCompanies();
    }
}