package com.example.nrs.Dto;

import com.example.nrs.Entity.Department;
import com.example.nrs.Entity.Note;
import com.example.nrs.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String userFullName;
    private String userEmail;
    private String userMobileNumber;
    private String userPassword;
    private String collegeName;
    private String collegeAddress;
    private boolean isActive = true;
    private Department department;
    private Role role;
    private List<Note> notes;
}
