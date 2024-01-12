package com.example.chillisaucek8s.domain.spaces.controller;

import com.example.chillisaucek8s.domain.spaces.dto.request.BoxRequestDto;
import com.example.chillisaucek8s.domain.spaces.service.BoxService;
import com.example.chillisaucek8s.global.message.ResponseMessage;
import com.example.chillisaucek8s.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @PostMapping("/boxes/{companyName}/{spaceId}")
    public ResponseEntity<ResponseMessage<String>> createBox
            (@PathVariable("companyName")String companyName, @PathVariable("spaceId") Long spaceId,
             @RequestBody BoxRequestDto boxRequestDto, @AuthenticationPrincipal UserDetailsImpl details) {
        boxService.createBox(companyName,spaceId, boxRequestDto, details);
        return ResponseMessage.responseSuccess("박스 생성 성공", "");
    }

    @PatchMapping("/boxes/{companyName}/{boxId}")
    public ResponseEntity<ResponseMessage<String>> updateBox
            (@PathVariable("companyName") String companyName,@PathVariable("boxId") Long boxId, @RequestBody BoxRequestDto boxRequestDto, @AuthenticationPrincipal UserDetailsImpl details){
        boxService.updateBox(companyName, boxId, boxRequestDto, details);
        return ResponseMessage.responseSuccess("박스 수정 성공","");
    }

    @DeleteMapping("/boxes/{companyName}/{boxId}")
    public ResponseEntity<ResponseMessage<String>> deleteBox
            (@PathVariable("companyName") String companyName, @PathVariable("boxId") Long boxId, @AuthenticationPrincipal UserDetailsImpl details){
        boxService.deleteBox(companyName, boxId,details);
        return ResponseMessage.responseSuccess("박스 삭제 완료","");
    }
}
