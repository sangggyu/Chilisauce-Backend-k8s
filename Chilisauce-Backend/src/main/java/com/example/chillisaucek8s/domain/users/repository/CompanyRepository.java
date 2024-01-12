package com.example.chillisaucek8s.domain.users.repository;

import com.example.chillisaucek8s.domain.users.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Companies, Long> {
    Optional<Companies> findByCompanyName(String companyName);

    Optional<Companies> findByCertification(String certification);

}
