package com.example.nrs.entity;

import com.example.nrs.dto.DepartmentDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "department", uniqueConstraints = {
        @UniqueConstraint(name = "uk_department_name",columnNames = "name")
})
@SQLDelete(sql = "UPDATE Department d SET d.isActive=false where d.id=?")
@Where(clause = "is_active=true")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String departmentName;

    @Column(name = "description",nullable = false)
    private String departmentDescription;

    @Column(name = "is_active")
    private boolean isActive = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "department")
    @JsonManagedReference(value = "users")
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "department")
    @JsonManagedReference(value = "department")
    private List<Semester> semesterList;

    public Department(DepartmentDto departmentDto){
        this.id=departmentDto.getId();
        this.departmentName=departmentDto.getDepartmentName();
        this.departmentDescription=departmentDto.getDepartmentDescription();
        this.isActive=departmentDto.isActive();
    }
}
