package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 수정 요청 DTO
 */
@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    
    private String name;
    private String country;
    private String language;
    
    public UserUpdateRequest(String name, String country, String language) {
        this.name = name;
        this.country = country;
        this.language = language;
    }
}