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
@Table(name = "course", uniqueConstraints = {
        @UniqueConstraint(name = "uk_course_name",columnNames = "name")
})
@SQLDelete(sql = "UPDATE Course c set c.isActive=true where c.id=?")
@Where(clause = "is_active=true")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name",unique = true,nullable = false)
    private String courseName;

    @Column(name="description")
    private String courseDescription;

    @Column(name = "is_active")
    private Boolean isActive=Boolean.TRUE;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id",referencedColumnName = "id")
    @JsonBackReference(value = "semester")
    private Semester semester;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "course")
    @JsonManagedReference(value = "course")
    private List<Note> noteList;
}
