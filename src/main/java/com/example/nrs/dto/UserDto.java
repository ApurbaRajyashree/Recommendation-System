package com.example.nrs.dto;

import com.example.nrs.entity.Department;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Role;
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

    public UserDto(User user){
        this.id=user.getId();
        this.userFullName=user.getUserFullName();
        this.userEmail=user.getUserEmail();
        this.userPassword=user.getUserPassword();
        this.userMobileNumber= user.getUserMobileNumber();
        this.role=user.getRole();
        this.department=user.getDepartment();
        this.collegeName= user.getCollegeName();
        this.collegeAddress=user.getCollegeAddress();
        this.isActive=user.isActive();
    }
}
