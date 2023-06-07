package com.example.nrs.service;

import com.example.nrs.dto.CourseDto;
import com.example.nrs.entity.SemesterName;

import java.util.List;

public interface CourseService {
    String createCourse(CourseDto courseDto);

    List<CourseDto> getAllCourses();

    public List<CourseDto> findCourseBySemesterName(SemesterName semesterName);

    List<CourseDto> findAllCourseBySemesterId(Integer id);

    String updateCourse(Integer id,CourseDto courseDto);

    String deleteCourse(Integer id);
}
