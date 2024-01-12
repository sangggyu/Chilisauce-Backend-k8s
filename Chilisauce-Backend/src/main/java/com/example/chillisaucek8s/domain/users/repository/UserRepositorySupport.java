package com.example.chillisaucek8s.domain.users.repository;

import com.example.chillisaucek8s.domain.users.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositorySupport {
    Optional<User> findByIdAndCompanies_CompanyName(Long id, String companyName);

    List<User> findAllByCompanies_CompanyName(String companyName);

    List<User> findAllByUsernameContainingAndCompanies(String name, String companyName);
}