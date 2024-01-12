package com.example.chillisaucek8s.domain.spaces.entity;


import com.example.chillisaucek8s.domain.spaces.dto.request.MultiBoxRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@RequiredArgsConstructor
public class MultiBox extends Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public MultiBox(MultiBoxRequestDto multiBoxRequestDto, Space space) {
        super(multiBoxRequestDto.getMultiBoxName(), multiBoxRequestDto.getX(), multiBoxRequestDto.getY(), space);
    }


    public MultiBox(String locationName, String x, String y) {
        super(locationName, x, y);
    }

    @Builder
    public MultiBox(Long id,String locationName, String x, String y) {
        super(id, locationName, x, y);
    }

    public MultiBox(String multiBoxName, String x, String y, Space space) {
        this.setLocationName(multiBoxName);
        this.setX(x);
        this.setY(y);
        this.setSpace(space);
    }


    public void updateMultiBox(MultiBoxRequestDto multiBoxRequestDto) {
        this.setLocationName(multiBoxRequestDto.getMultiBoxName());
        this.setX(multiBoxRequestDto.getX());
        this.setY(multiBoxRequestDto.getY());
    }

    @Override
    public boolean isBox() {
        return false;
    }

    @Override
    public boolean isMultiBox() {
        return true;
    }

    @Override
    public boolean isMr() {
        return false;
    }
}
