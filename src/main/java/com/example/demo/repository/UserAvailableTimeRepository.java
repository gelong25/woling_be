package com.example.demo.repository;

import com.example.demo.domain.AvailableTime;
import com.example.demo.domain.TimeSlot;
import com.example.demo.domain.User;
import com.example.demo.domain.UserAvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAvailableTimeRepository extends JpaRepository<UserAvailableTime, Long> {
    
    List<UserAvailableTime> findByUser(User user);
    
    List<UserAvailableTime> findByUserUserId(Long userId);
    
    List<UserAvailableTime> findByAvailableTime(AvailableTime availableTime);
    
    Optional<UserAvailableTime> findByUserAndAvailableTime(User user, AvailableTime availableTime);
    
    boolean existsByUserAndAvailableTime(User user, AvailableTime availableTime);
    
    @Query("SELECT uat FROM UserAvailableTime uat JOIN FETCH uat.availableTime WHERE uat.user.userId = :userId")
    List<UserAvailableTime> findByUserIdWithAvailableTime(@Param("userId") Long userId);
    
    @Query("SELECT uat FROM UserAvailableTime uat WHERE uat.user.userId = :userId AND uat.availableTime.timeSlot = :timeSlot")
    Optional<UserAvailableTime> findByUserIdAndTimeSlot(@Param("userId") Long userId, @Param("timeSlot") TimeSlot timeSlot);
    
    @Query("SELECT u FROM User u JOIN u.userAvailableTimes uat WHERE uat.availableTime.timeSlot = :timeSlot")
    List<User> findUsersByTimeSlot(@Param("timeSlot") TimeSlot timeSlot);
    
    void deleteByUserAndAvailableTime(User user, AvailableTime availableTime);
}