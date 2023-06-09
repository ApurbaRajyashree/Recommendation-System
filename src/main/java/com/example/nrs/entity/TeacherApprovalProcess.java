package com.example.nrs.entity;

import com.example.nrs.dto.TeacherApprovalProcessDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity
@Table(name = "teacher_approval_process")
public class TeacherApprovalProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience" , nullable = false)
    private TeacherExperience teacherExperience;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "detail", columnDefinition = "TEXT")
    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_qualification", nullable = false)
    private EducationQualification educationQualification;

    @Column(name = "university_or_college", nullable = false)
    private String universityOrCollegeName;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;


    public TeacherApprovalProcess(TeacherApprovalProcessDto teacherApprovalProcessDto){
        this.id=teacherApprovalProcessDto.getId();
        this.user=teacherApprovalProcessDto.getUser();
        this.date=teacherApprovalProcessDto.getDate();
        this.detail=teacherApprovalProcessDto.getDetail();
        this.educationQualification=teacherApprovalProcessDto.getEducationQualification();
        this.universityOrCollegeName=teacherApprovalProcessDto.getUniversityOrCollegeName();
        this.remarks=teacherApprovalProcessDto.getRemarks();
        this.status=teacherApprovalProcessDto.getStatus();
        this.teacherExperience=teacherApprovalProcessDto.getTeacherExperience();
        this.fileName=teacherApprovalProcessDto.getFileName();
        this.filePath=teacherApprovalProcessDto.getFilePath();
    }
}
