package com.example.demo.repository;

import com.example.demo.domain.Report;
import com.example.demo.domain.ReportType;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    
    List<Report> findByReportedUser(User reportedUser);
    
    List<Report> findByReporter(User reporter);
    
    List<Report> findByReportType(ReportType reportType);
    
    List<Report> findByReportedUserOrderByCreatedAtDesc(User reportedUser);
    
    @Query("SELECT COUNT(r) FROM Report r WHERE r.reportedUser = :user")
    Long countByReportedUser(@Param("user") User user);
    
    @Query("SELECT r FROM Report r WHERE r.reportedUser.userId = :userId ORDER BY r.createdAt DESC")
    List<Report> findByReportedUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);
    
    @Query("SELECT r FROM Report r WHERE r.reporter = :reporter AND r.reportedUser = :reportedUser")
    Optional<Report> findByReporterAndReportedUser(@Param("reporter") User reporter, @Param("reportedUser") User reportedUser);
    
    boolean existsByReporterAndReportedUser(User reporter, User reportedUser);
    
    @Query("SELECT r FROM Report r WHERE r.createdAt BETWEEN :startDate AND :endDate")
    List<Report> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}