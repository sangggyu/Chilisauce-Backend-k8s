package com.example.chillisaucek8s.domain.users.service;

import com.example.chillisaucek8s.domain.users.dto.response.UserDetailResponseDto;
import com.example.chillisaucek8s.domain.users.entity.User;
import com.example.chillisaucek8s.domain.users.exception.UserErrorCode;
import com.example.chillisaucek8s.domain.users.exception.UserException;
import com.example.chillisaucek8s.domain.users.repository.UserRepository;
import com.example.chillisaucek8s.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final UserRepository userRepository;

    public List<UserDetailResponseDto> searchUser(String name, UserDetailsImpl userDetails) {
        User finder = userDetails.getUser();
        String companyName = finder.getCompanies().getCompanyName();
        List<User> users = userRepository.findAllByUsernameContainingAndCompanies(name, companyName);

        if (users.isEmpty()) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        return users.stream().map(UserDetailResponseDto::new).toList();
    }
}
