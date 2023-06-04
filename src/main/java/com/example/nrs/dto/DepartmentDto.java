package com.example.nrs.dto;

import com.example.nrs.entity.Department;
import com.example.nrs.entity.Semester;
import com.example.nrs.entity.User;
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

    public DepartmentDto(Department department){
        this.id=department.getId();
        this.departmentName=department.getDepartmentName();
        this.departmentDescription=department.getDepartmentDescription();
        this.isActive=department.isActive();
    }
}
