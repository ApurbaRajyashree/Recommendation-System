package com.example.nrs.repository;

import com.example.nrs.entity.Course;
import com.example.nrs.entity.SemesterName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

    @Query(value = "select * from course where  semester_id=?1", nativeQuery = true)
    List<Course> findAllBySemester_Id(Integer id);

    List<Course> findAllBySemester_SemesterName(SemesterName semesterName);
}
