package com.example.nrs.controller.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.example.nrs.entity.Note;
import com.example.nrs.repository.NoteRepo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteDownloadController {

    private final NoteRepo noteRepo;

    public NoteDownloadController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping(  "/download/{noteId}")
    public ResponseEntity<Object> downloadFile(@PathVariable("noteId") Integer noteId) throws IOException {
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
}
