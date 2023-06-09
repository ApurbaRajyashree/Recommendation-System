package com.example.nrs.controller.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.example.nrs.entity.Note;
import com.example.nrs.entity.TeacherApprovalProcess;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.TeacherApprovalProcessRepo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DownloadController {

    private final NoteRepo noteRepo;

    private final TeacherApprovalProcessRepo teacherApprovalProcessRepo;

    public DownloadController(NoteRepo noteRepo, TeacherApprovalProcessRepo teacherApprovalProcessRepo) {
        this.noteRepo = noteRepo;
        this.teacherApprovalProcessRepo = teacherApprovalProcessRepo;
    }

    @GetMapping(  "/download/{noteId}")
    public ResponseEntity<Object> downloadNoteFile(@PathVariable("noteId") Integer noteId) throws IOException {
        Note note=noteRepo.findById(noteId).get();
        String filename = note.getFilePath();
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    @GetMapping(  "/download/teacher-resume/{teacherId}")
    public ResponseEntity<Object> downloadResumeFile(@PathVariable("teacherId") Integer teacherId) throws IOException {
        TeacherApprovalProcess teacherApprovalProcess=teacherApprovalProcessRepo.findById(teacherId).get();
        String filename = teacherApprovalProcess.getFilePath();
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
}
