package com.example.nrs.dto;

import com.example.nrs.entity.Status;
import com.example.nrs.entity.TeacherExperience;
import com.example.nrs.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherApprovalProcessDto {

    private Integer id;

    private User user;

    private TeacherExperience teacherExperience;

    private Date date;

    private String filePath;

    private String remarks;

    private Status status;

    private TeacherApprovalProcessDto(Integer id){
        this.id=id;
    }
}

