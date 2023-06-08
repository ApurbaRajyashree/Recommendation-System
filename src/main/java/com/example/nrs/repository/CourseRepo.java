package com.example.nrs.repository;

import com.example.nrs.entity.Course;
import com.example.nrs.entity.SemesterName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

    @Query(value = "select * from course where  semester_id=?1", nativeQuery = true)
    List<Course> findAllBySemester_Id(Integer id);

    List<Course> findAllBySemester_SemesterName(SemesterName semesterName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Course  SET is_active = false  WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
