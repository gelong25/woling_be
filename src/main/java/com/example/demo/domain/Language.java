package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "language")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;
    
    @Column(name = "language_code", nullable = false, unique = true, length = 10)
    private String languageCode;
    
    @Column(name = "language_name", nullable = false, length = 100)
    private String languageName;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserLanguage> userLanguages = new ArrayList<>();

}