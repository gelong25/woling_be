package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "available_times")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AvailableTime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_time_id")
    private Long availableTimeId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "time_slot", nullable = false, unique = true)
    private TimeSlot timeSlot;
    
    @OneToMany(mappedBy = "availableTime", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserAvailableTime> userAvailableTimes = new ArrayList<>();
}