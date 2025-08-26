package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.Language;
import com.example.demo.domain.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    
    List<UserLanguage> findByUser(User user);
    
    List<UserLanguage> findByUserUserId(Long userId);
    
    List<UserLanguage> findByLanguage(Language language);
    
    Optional<UserLanguage> findByUserAndLanguage(User user, Language language);
    
    boolean existsByUserAndLanguage(User user, Language language);
    
    @Query("SELECT ul FROM UserLanguage ul JOIN FETCH ul.language WHERE ul.user.userId = :userId")
    List<UserLanguage> findByUserIdWithLanguage(@Param("userId") Long userId);
}