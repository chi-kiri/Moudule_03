package com.example.course_management.repository;

import com.example.course_management.dto.CourseResponseV2;
import com.example.course_management.model.Course;
import com.example.course_management.model.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
            SELECT c
            FROM Course c
            WHERE c.status = :status
            """)
    Page<Course> findAllByStatus(
            @Param("status") CourseStatus status,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.course_management.dto.CourseResponseV2(
                c.id,
                c.title,
                c.status
            )
            FROM Course c
            WHERE c.status = :status
            """)
    Page<CourseResponseV2> findAllProjectedByStatus(
            @Param("status") CourseStatus status,
            Pageable pageable
    );
}