package com.example.demo.repository;

import com.example.demo.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    
    Optional<Language> findByLanguageCode(String languageCode);
    
    boolean existsByLanguageCode(String languageCode);
    
    Optional<Language> findByLanguageName(String languageName);
}