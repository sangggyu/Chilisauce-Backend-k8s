package com.example.chillisaucek8s.domain.spaces.entity;


import com.example.chillisaucek8s.domain.spaces.dto.request.FloorRequestDto;
import com.example.chillisaucek8s.domain.users.entity.Companies;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty
    private String floorName;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Space> spaces;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companies_id", nullable = false)
    private Companies companies;

    public Floor(FloorRequestDto floorRequestDto, Companies companies) {
        this.floorName = floorRequestDto.getFloorName();
        this.companies = companies;
    }

    public void updateFloor(FloorRequestDto floorRequestDto) {
        this.floorName = floorRequestDto.getFloorName();
    }

    public void updateFloor(List<Space> spaces) {
        this.spaces = spaces;
    }
}