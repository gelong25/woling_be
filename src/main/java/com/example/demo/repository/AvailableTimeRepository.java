package com.example.demo.repository;

import com.example.demo.domain.AvailableTime;
import com.example.demo.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
    
    Optional<AvailableTime> findByTimeSlot(TimeSlot timeSlot);
    
    boolean existsByTimeSlot(TimeSlot timeSlot);
}