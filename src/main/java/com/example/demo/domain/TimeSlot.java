package com.example.demo.domain;

public enum TimeSlot {
    MORNING("오전"),
    AFTERNOON("오후"),
    EVENING("저녁");
    
    private final String description;
    
    TimeSlot(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}