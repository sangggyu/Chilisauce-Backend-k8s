package com.example.chillisaucek8s.domain.users.service;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
}
