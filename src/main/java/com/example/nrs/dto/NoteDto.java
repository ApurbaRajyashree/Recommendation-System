package com.example.nrs.dto;

import com.example.nrs.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private Integer id;
    private String noteDescription;
    private String noteTitle;
    private String dateOfNoteCreation;
    private String filePath;
    private Status noteStatus;
    private Boolean isActive;

    private MultipartFile multipartFile;
    private Course course;
    private User user;

    public NoteDto(Note note){
        this.id=note.getId();
        this.filePath=note.getFilePath();
        this.noteTitle=note.getNoteTitle();
        this.noteDescription=note.getNoteDescription();
        this.noteStatus=note.getNoteStatus();
        this.course=note.getCourse();
        this.user=note.getUser();
        this.isActive=note.getIsActive();
        this.dateOfNoteCreation=note.getDateOfNoteCreation();
    }

    public NoteDto(Integer id){
        this.id=id;
    }
}
