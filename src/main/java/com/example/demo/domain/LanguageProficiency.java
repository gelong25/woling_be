package com.example.demo.domain;

public enum LanguageProficiency {
    LOW("초급"),
    MIDDLE("중급"),
    HIGH("고급");
    
    private final String description;
    
    LanguageProficiency(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}