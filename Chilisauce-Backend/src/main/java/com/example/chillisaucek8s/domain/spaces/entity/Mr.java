package com.example.chillisaucek8s.domain.spaces.entity;


import com.example.chillisaucek8s.domain.reservations.entity.Reservation;
import com.example.chillisaucek8s.domain.spaces.dto.request.MrRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Mr extends Location{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "meetingRoom")
    List<Reservation> reservations = new ArrayList<>();


    public Mr(MrRequestDto mrRequestDto, Space space) {
        super(mrRequestDto.getMrName(), mrRequestDto.getX(), mrRequestDto.getY(), space);
    }


    @Builder
    public Mr(Long id, String locationName, String x, String y, List<Reservation> reservation) {
        super(locationName, x, y);
        this.id= id;
        this.reservations = reservation;
    }

    public Mr(String mrName, String x, String y, Space space) {
        this.setLocationName(mrName);
        this.setX(x);
        this.setY(y);
        this.setSpace(space);
    }


    public void updateMr(MrRequestDto mrRequestDto) {
        this.setLocationName(mrRequestDto.getMrName());
        this.setX(mrRequestDto.getX());
        this.setY(mrRequestDto.getY());
    }

    @Override
    public boolean isBox() {
        return false;
    }

    @Override
    public boolean isMultiBox() {
        return false;
    }

    @Override
    public boolean isMr() {
        return true;
    }
}