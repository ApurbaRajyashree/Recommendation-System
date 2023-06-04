package com.example.nrs.Dto;

import com.example.nrs.Entity.Semester;
import com.example.nrs.Entity.User;
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
public class DepartmentDto {
    private Integer id;

    private String departmentName;

    private String departmentDescription;

    private boolean isActive = true;

    private List<User> users;

    private List<Semester> semesterList;
}
