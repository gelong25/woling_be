package com.example.demo.domain;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 엔티티
 */
@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 100)
    private String country; // 국적

    @Column(nullable = false)
    private LocalDate birth;  // 생년월일

    @Column(name = "region_name", length = 200)
    private String regionName; // 전체 주소 ex) 서울시 강남구 ~~로 ~건물 ~~

    @Column(name = "side_region_name", length = 200)
    private String sideRegionName; // 부분 주소 ex) 서울시 강남구

    @Column(nullable = false, name = "gender_verified")
    private Boolean genderVerified; // OCR을 통한 성별 인증 여부


    @Column(nullable = false, name = "has_child")
    private Boolean hasChild; // 아이 여부 (선택적)

    @Column
    private String mbti; // 사용자 obti ( 선택적)

    @Column
    private String interests; // 관심사 ->(사용 안할듯)

    @Column(name = "popularity_count")
    @Builder.Default
    private Integer popularityCount = 0; // 사용자 인기도

    @Column(name = "reported_count")
    @Builder.Default
    private Integer reportedCount = 0; // 신고당한 횟수


    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Builder.Default
    private UserRole userRole = UserRole.USER; // 사용자 권한: ADMIN(관리자) , USER(사용자)

    //생성,수정,탈퇴 날짜
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PostLike> postLikes = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VerifiedPost> verifiedPosts = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VerifiedComment> verifiedComments = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VerifiedLike> verifiedLikes = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChatMessage> chatMessages = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChatRoomParticipant> chatRoomParticipants = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserLanguage> userLanguages = new ArrayList<>();
    
    @OneToMany(mappedBy = "reportedUser", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Report> receivedReports = new ArrayList<>();
    
    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Report> submittedReports = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserAvailableTime> userAvailableTimes = new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * 회원 탈퇴 (Soft Delete)
     */
    public void withdraw() {
        this.deletedAt = LocalDateTime.now();
    }
    
    /**
     * 탈퇴 여부 확인
     */
    public boolean isDeleted() {
        return deletedAt != null;
    }
    
    /**
     * 활성 상태 확인
     */
    public boolean isActive() {
        return deletedAt == null;
    }
    
    /**
     * 회원 정보 업데이트
     */
    public void updateUserInfo(String name, String country) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
        if (country != null) {
            this.country = country.trim().isEmpty() ? null : country.trim();
        }
    }
    
    /**
     * 신고 카운트 증가
     */
    public void increaseReportCount() {
        this.reportedCount++;
    }
    
    /**
     * 신고 카운트 조회
     */
    public Integer getReportCount() {
        return this.reportedCount;
    }
    
    /**
     * 현재 나이 계산
     */
    public int getAge() {
        return Period.between(this.birth, LocalDate.now()).getYears();
    }
}