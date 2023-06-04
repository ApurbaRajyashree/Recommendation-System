package com.example.nrs.Dto;

import com.example.nrs.Entity.Comment;
import com.example.nrs.Entity.Course;
import com.example.nrs.Entity.Status;
import com.example.nrs.Entity.User;
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
    private Boolean isActive;
    private Course course;
    private User user;
    private List<Comment> commentList;
}
