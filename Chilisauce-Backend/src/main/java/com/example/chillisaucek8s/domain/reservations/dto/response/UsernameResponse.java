package com.example.chillisaucek8s.domain.reservations.dto.response;

import com.example.chillisaucek8s.domain.reservations.dto.ReservationUserWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsernameResponse {
    String username;

    public UsernameResponse(ReservationUserWrapper dto){
        username = dto.getUsername();
    }
}