package com.example.demo.dto;

import com.example.demo.domain.UserRole;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private Long userId;
    private String email;
    private String name;
    private String country;
    private Integer age;
    private String profileImageUrl;
    private Boolean genderVerified;
    private String language;
    private UserRole userRole;
    private LocalDateTime createdAt;
    private boolean isActive;

    // 필수 필드 생성자
    public UserDto(Long userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}