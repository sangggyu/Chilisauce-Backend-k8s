package com.example.chillisaucek8s.domain.users.util;

import com.example.chillisaucek8s.domain.users.entity.Companies;
import com.example.chillisaucek8s.domain.users.entity.User;
import com.example.chillisaucek8s.domain.users.entity.UserRoleEnum;
import com.example.chillisaucek8s.domain.users.exception.UserErrorCode;
import com.example.chillisaucek8s.domain.users.exception.UserException;
import com.example.chillisaucek8s.domain.users.repository.CompanyRepository;
import com.example.chillisaucek8s.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 관리자 회원가입 시 5명의 기본 유저 생성
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class BaseUserFactory {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void makeBaseUser(String companyName) {
        Companies company = companyRepository.findByCompanyName(companyName)
                .orElseThrow(() -> new UserException(UserErrorCode.COMPANY_NOT_FOUND));

        List<String> userInformationList = List.of("홍길동 test1@test" + company.getId() + ".com",
                "채소연 test2@test" + company.getId() + ".com",
                "김철수 test3@test" + company.getId() + ".com",
                "윤대협 test4@test" + company.getId() + ".com",
                "성춘향 test5@test" + company.getId() + ".com");
        List<User> userList = userInformationList.stream().map(x -> {
                    String[] str = x.split(" ");
                    return User.builder()
                            .username(str[0])
                            .role(UserRoleEnum.USER)
                            .companies(company)
                            .password(passwordEncoder.encode("1q2w3e4r!"))
                            .email(str[1])
                            .build();
                }
        ).toList();
        userRepository.saveAll(userList);
    }
}