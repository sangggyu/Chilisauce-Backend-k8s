package com.example.chillisaucek8s.domain.spaces.repository;

import com.example.chillisaucek8s.domain.spaces.entity.MultiBox;
import com.example.chillisaucek8s.domain.users.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultiBoxRepository extends JpaRepository<MultiBox, Long> {

    Optional<MultiBox> findByIdAndSpaceCompanies(Long multiBoxId, Companies companies);

}
