package com.example.demo.domain;

public enum VerifiedPostCategory {
    PARENTING("육아"),
    KOREAN_LANGUAGE("한국어"),
    CULTURE("문화"),
    FREE("자유");
    
    private final String description;
    
    VerifiedPostCategory(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}