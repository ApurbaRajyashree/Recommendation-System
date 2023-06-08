package com.example.nrs.dto;

import com.example.nrs.entity.*;
import jakarta.persistence.*;
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

    private String detail;

    private EducationQualification educationQualification;

    private String universityOrCollegeName;

    private String remarks;

    private Status status;

    public TeacherApprovalProcessDto(TeacherApprovalProcess teacherApprovalProcess){
        this.id=teacherApprovalProcess.getId();
        this.user=teacherApprovalProcess.getUser();
        this.date=teacherApprovalProcess.getDate();
        this.detail=teacherApprovalProcess.getDetail();
        this.educationQualification=teacherApprovalProcess.getEducationQualification();
        this.universityOrCollegeName=teacherApprovalProcess.getUniversityOrCollegeName();
        this.remarks=teacherApprovalProcess.getRemarks();
        this.status=teacherApprovalProcess.getStatus();
    }
}

