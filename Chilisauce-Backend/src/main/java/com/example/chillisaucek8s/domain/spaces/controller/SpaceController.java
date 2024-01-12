package com.example.chillisaucek8s.domain.spaces.controller;

import com.example.chillisaucek8s.domain.spaces.dto.request.SpaceRequestDto;
import com.example.chillisaucek8s.domain.spaces.dto.response.SpaceListResponseDto;
import com.example.chillisaucek8s.domain.spaces.dto.response.SpaceResponseDto;
import com.example.chillisaucek8s.domain.spaces.service.SpaceService;
import com.example.chillisaucek8s.global.message.ResponseMessage;
import com.example.chillisaucek8s.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpaceController {
    private final SpaceService spaceService;

    //플로우 안에 공간 생성
    @PostMapping("/spaces/{companyName}/{floorId}")
    public ResponseEntity<ResponseMessage<String>> createSpaceInFloor
    (@PathVariable("companyName") String companyName, @RequestBody SpaceRequestDto spaceRequestDto, @AuthenticationPrincipal UserDetailsImpl details, @PathVariable("floorId") Long floorId) {
        spaceService.createSpaceInFloor(companyName, spaceRequestDto, details, floorId);
        return ResponseMessage.responseSuccess("공간 생성 성공","");
    }
    //공간 생성
    @PostMapping("/spaces/{companyName}")
    public ResponseEntity<ResponseMessage<String>> createSpace
    (@PathVariable("companyName") String companyName, @RequestBody SpaceRequestDto spaceRequestDto, @AuthenticationPrincipal UserDetailsImpl details) {
        spaceService.createSpace(companyName, spaceRequestDto, details);
        return ResponseMessage.responseSuccess("공간 생성 성공","");
    }

    //공간 전체조회
    @GetMapping("/spaces/{companyName}")
    public ResponseEntity<ResponseMessage<List<SpaceListResponseDto>>> allSpacelist
    (@PathVariable("companyName") String companyName,@AuthenticationPrincipal UserDetailsImpl details) {

        return ResponseMessage.responseSuccess("공간 조회 성공",spaceService.allSpacelist(companyName, details));
    }

    //공간 선택조회
    @GetMapping("/spaces/{companyName}/{spaceId}")
    public ResponseEntity<ResponseMessage<List<SpaceResponseDto>>> getSpacelist
    (@PathVariable("companyName") String companyName, @PathVariable("spaceId") Long spaceId, @AuthenticationPrincipal UserDetailsImpl details) {

        return ResponseMessage.responseSuccess("공간 조회 성공",spaceService.getSpacelist(companyName, spaceId, details));
    }
    //공간 개별 수정
    @PatchMapping("/spaces/{companyName}/{spaceId}")
    public ResponseEntity<ResponseMessage<String>> updateSpace
    (@PathVariable("companyName") String companyName, @PathVariable("spaceId") Long spaceId,
     @RequestBody SpaceRequestDto spaceRequestDto, @AuthenticationPrincipal UserDetailsImpl details) {
        spaceService.updateSpace(companyName, spaceId, spaceRequestDto,details);
        return ResponseMessage.responseSuccess("공간 수정 성공","");
    }
    //공간 삭제
    @DeleteMapping("/spaces/{companyName}/{spaceId}")
    public ResponseEntity<ResponseMessage<String>> deleteSpace
    (@PathVariable("companyName") String companyName, @PathVariable("spaceId") Long spaceId, @AuthenticationPrincipal UserDetailsImpl details){
        spaceService.deleteSpace(companyName,spaceId,details);
        return ResponseMessage.responseSuccess("공간 삭제 성공","");
    }
}