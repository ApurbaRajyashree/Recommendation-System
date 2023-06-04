package com.example.nrs.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="semester")
@SQLDelete(sql = "UPDATE Semester s set s.isActive=true where s.id=?")
@Where(clause = "is_active=true")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name",unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private SemesterName semesterName;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="department_id",referencedColumnName = "id")
    @JsonBackReference(value = "department")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "semester")
    @JsonManagedReference(value = "semester")
    private List<Course> courseList;

    @Column(name = "is_active")
    private boolean isActive=Boolean.TRUE;
}
