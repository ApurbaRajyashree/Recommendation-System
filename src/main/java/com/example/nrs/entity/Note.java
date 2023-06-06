package com.example.nrs.entity;

import com.example.nrs.dto.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "note")
@SQLDelete(sql = "UPDATE Note n set n.isActive=true where n.id=?")
@Where(clause = "is_active=false")

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", nullable = false)
    private String noteDescription;

    @Column(name = "title", nullable = false, length = 100)
    private String noteTitle;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "date_of_note_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfNoteCreation;

    @Column(name = "file_path", length = 200, nullable = false)
    private String filePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status noteStatus;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "remark")
    private String remark;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference(value = "course")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "users")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "note")
    @JsonManagedReference(value = "comment")
    private List<Comment> commentList;

    public Note(NoteDto noteDto){
        this.id=noteDto.getId();
        this.fileName=noteDto.getFileName();
        this.filePath=noteDto.getFilePath();
        this.noteTitle=noteDto.getNoteTitle();
        this.noteDescription=noteDto.getNoteDescription();
        this.noteStatus=noteDto.getNoteStatus();
        this.course=noteDto.getCourse();
        this.user=noteDto.getUser();
        this.isActive=noteDto.getIsActive();
        this.remark=noteDto.getRemark();
        this.dateOfNoteCreation=noteDto.getDateOfNoteCreation();
    }
}
