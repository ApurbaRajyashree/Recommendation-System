package com.example.nrs.dto;

import com.example.nrs.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String fileName;
    private Date dateOfNoteCreation;
    private String filePath;
    private Status noteStatus;
    private String remark;
    private Boolean isActive;
    private Course course;
    private User user;
    private List<Comment> commentList;

    public NoteDto(Note note){
        this.id=note.getId();
        this.fileName=note.getFileName();
        this.filePath=note.getFilePath();
        this.noteTitle=note.getNoteTitle();
        this.noteDescription=note.getNoteDescription();
        this.noteStatus=note.getNoteStatus();
        this.course=note.getCourse();
        this.user=note.getUser();
        this.isActive=note.getIsActive();
        this.remark=note.getRemark();
        this.dateOfNoteCreation=note.getDateOfNoteCreation();
    }
}
