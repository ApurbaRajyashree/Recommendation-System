package com.example.nrs.entity;

import com.example.nrs.dto.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "note")
@Where(clause = "is_active=false")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String noteDescription;

    @Column(name = "title", nullable = false, length = 100)
    private String noteTitle;

    @Column(name = "date_of_note_creation",length = 200, nullable = false)
    private String dateOfNoteCreation;

    @Column(name = "file_path", length = 200, nullable = false)
    private String filePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status noteStatus;

    @Column(name = "is_active")
    private Boolean isActive;



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonBackReference(value = "course")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "users")
    private User user;

//    public Note(NoteDto noteDto){
//        this.id=noteDto.getId();
//        this.fileName=noteDto.getFileName();
//        this.filePath=noteDto.getFilePath();
//        this.noteTitle=noteDto.getNoteTitle();
//        this.noteDescription=noteDto.getNoteDescription();
//        this.noteStatus=noteDto.getNoteStatus();
//        this.course=noteDto.getCourse();
//        this.user=noteDto.getUser();
//        this.isActive=noteDto.getIsActive();
//        this.dateOfNoteCreation=noteDto.getDateOfNoteCreation();
//    }


}
