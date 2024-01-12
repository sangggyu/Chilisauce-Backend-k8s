package com.example.chillisaucek8s.domain.spaces.dto.response;

import com.example.chillisaucek8s.domain.spaces.entity.Space;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponseDto {

    private Long spaceId;
    private String spaceName;
    private Long floorId;
    private String floorName;
    private List<BoxResponseDto> boxList = new ArrayList<>();
    private List<MrResponseDto> mrList = new ArrayList<>();
    private List<MultiBoxResponseDto> multiBoxList = new ArrayList<>();


    public SpaceResponseDto(Space space) {
        this.spaceId = space.getId();
        this.spaceName = space.getSpaceName();

    }
    public SpaceResponseDto(Long id, String spaceName) {
        this.spaceId = id;
        this.spaceName = spaceName;
    }

    @Builder
    public SpaceResponseDto(Space space, Long floorId, String floorName, List<BoxResponseDto> boxList, List<MrResponseDto> mrList, List<MultiBoxResponseDto> multiBoxList) {
        this.spaceId = space.getId();
        this.spaceName = space.getSpaceName();
        this.floorId = floorId;
        this.floorName = floorName;
        this.boxList = boxList;
        this.mrList = mrList;
        this.multiBoxList = multiBoxList;

    }

}
