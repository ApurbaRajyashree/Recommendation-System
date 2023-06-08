package com.example.nrs.entity;

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

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="experience")
    private TeacherExperience teacherExperience;

    @Column(name = "date")
    private Date date;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private Status status;
}
