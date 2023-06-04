package com.example.nrs.entity;

import com.example.nrs.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_mobile_number", columnNames = {"mobile_number"}),
        @UniqueConstraint(name = "uk_email", columnNames = {"email"})
})
@SQLDelete(sql = "UPDATE User u SET u.isActive=false where u.id=?")
@Where(clause = "is_active=true")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name",length = 50)
    private String userFullName;

    @Column(name = "email", nullable = false, length = 50)
    private String userEmail;
    @Column(name = "mobile_number", nullable = false, length = 13)
    private String userMobileNumber;

    @Column(name = "password", nullable = false, length = 200)
    private String userPassword;

    @Column(name = "college_name",nullable = false,length = 100)
    private String collegeName;

    @Column(name = "college_address",nullable = false)
    private String collegeAddress;
    @Column(name = "is_active")
    private boolean isActive=true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "department")
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_notes",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id")
    )
    private List<Note> notes;


    public User(UserDto userDto){
        this.id=userDto.getId();
        this.userFullName=userDto.getUserFullName();
        this.userEmail=userDto.getUserEmail();
        this.userPassword=userDto.getUserPassword();
        this.userMobileNumber= userDto.getUserMobileNumber();
        this.role=userDto.getRole();
        this.department=userDto.getDepartment();
        this.collegeName= userDto.getCollegeName();
        this.collegeAddress=userDto.getCollegeAddress();
        this.isActive=userDto.isActive();
    }
}
