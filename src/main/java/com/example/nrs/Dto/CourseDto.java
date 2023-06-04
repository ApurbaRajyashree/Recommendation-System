package com.example.nrs.Dto;

import com.example.nrs.Entity.Note;
import com.example.nrs.Entity.Semester;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
}
