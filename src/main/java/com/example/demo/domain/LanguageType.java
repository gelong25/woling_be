package com.example.demo.domain;

public enum LanguageType {
    LEARNING("배우고 싶은 언어"),
    AVAILABLE("가능한 언어");
    
    private final String description;
    
    LanguageType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}