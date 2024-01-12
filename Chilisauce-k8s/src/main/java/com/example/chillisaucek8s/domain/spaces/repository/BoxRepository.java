package com.example.chillisaucek8s.domain.spaces.repository;

import com.example.chillisaucek8s.domain.spaces.entity.Box;
import com.example.chillisaucek8s.domain.users.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {
    Optional<Box> findByIdAndSpaceCompanies(Long boxId, Companies companies);


}