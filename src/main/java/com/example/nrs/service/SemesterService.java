package com.example.nrs.service;

import com.example.nrs.dto.SemesterDto;
import com.example.nrs.dto.SemesterRequestDto;

import java.util.List;

public interface SemesterService {
    String createSemester(SemesterDto semesterDto);

    String  createSemesters(SemesterRequestDto semesterRequestDtoDto);


    List<SemesterDto> getAllSemester();
    public List<SemesterDto> getSemestersByDepartment(Integer id);

    String deleteSemester(Integer id);
}
