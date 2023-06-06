package com.example.nrs.dto;

import com.example.nrs.entity.Course;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
    private Integer id;

    private String courseName;

    private String courseDescription;

    private Boolean isActive=Boolean.TRUE;

    private Semester semester;

    private List<Note> noteList;

    public CourseDto(Course course){
        this.id=course.getId();
        this.courseName=course.getCourseName();
        this.courseDescription=course.getCourseDescription();
        this.isActive=course.getIsActive();
        this.semester=course.getSemester();
    }
}
