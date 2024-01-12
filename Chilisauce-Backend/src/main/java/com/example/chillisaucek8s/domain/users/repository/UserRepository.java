package com.example.chillisaucek8s.domain.users.repository;

import com.example.chillisaucek8s.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySupport {
    Optional<User> findByEmail(String email);

    List<User> findAllByIdInAndCompanies_CompanyName(List<Long> userIds, String companyName);
}