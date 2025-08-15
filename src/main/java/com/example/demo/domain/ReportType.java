package com.example.demo.domain;

public enum ReportType {
    INAPPROPRIATE_CONTENT("부적절한 콘텐츠"),
    HARASSMENT("괴롭힘/욕설"),
    SPAM("스팸"),
    FAKE_PROFILE("허위 프로필"),
    INAPPROPRIATE_BEHAVIOR("부적절한 행동"),
    SCAM("사기"),
    OTHER("기타");
    
    private final String description;
    
    ReportType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}