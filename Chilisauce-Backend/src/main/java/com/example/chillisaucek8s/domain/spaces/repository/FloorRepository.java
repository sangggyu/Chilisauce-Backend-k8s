package com.example.chillisaucek8s.domain.spaces.repository;

import com.example.chillisaucek8s.domain.spaces.entity.Floor;
import com.example.chillisaucek8s.domain.users.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FloorRepository extends JpaRepository<Floor, Long>, FloorRepositorySupport{
    Optional<Floor> findByIdAndCompanies(Long floorId, Companies company);

}