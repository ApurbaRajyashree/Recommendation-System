package com.example.nrs.dto;

import com.example.nrs.entity.Course;
import com.example.nrs.entity.Department;
import com.example.nrs.entity.SemesterName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterRequestDto {
    private Integer id;
    private List<SemesterName> semesterNames;
    private Department department;

}
