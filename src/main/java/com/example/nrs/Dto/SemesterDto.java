package com.example.nrs.Dto;

import com.example.nrs.Entity.Course;
import com.example.nrs.Entity.Department;
import com.example.nrs.Entity.SemesterName;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto {
    private Integer id;
    private SemesterName semesterName;
    private Department department;
    private List<Course> courseList;
   private boolean isActive=Boolean.TRUE;
}
