package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    private String country;

    private Integer age;

    @Column(name = "profile_image_url", length = 200)
    private String profileImageUrl;

    @Column(name = "gender_verified")
    @Builder.Default
    private Boolean genderVerified = false;

    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Builder.Default
    private UserRole userRole = UserRole.USER;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     @Builder.Default
     private List<Post> posts = new ArrayList<>();

    // soft delete 확인 메서드
    public boolean isActive() {
        return deletedAt == null;
    }

}
