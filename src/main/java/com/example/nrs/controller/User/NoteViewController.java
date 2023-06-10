package com.example.nrs.controller.User;

import com.example.nrs.dto.NoteDto;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.service.NoteService;
import com.example.nrs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NoteViewController {

    private final NoteService noteService;
    private final UserService userService;

    private final NoteRepo noteRepo;

    public NoteViewController(NoteService noteService, UserService userService, NoteRepo noteRepo) {
        this.noteService = noteService;
        this.userService = userService;
        this.noteRepo = noteRepo;
    }


    @GetMapping("/user/note-view/{noteId}")
    public String noteView(Model model,@PathVariable("noteId")Integer noteId) {
        model.addAttribute("note",noteRepo.findNoteById(noteId));
        return "user/note-view";
    }

    @GetMapping("/user/self-note-view/{noteId}")
    public String selfNoteView(Model model,@PathVariable("noteId")Integer noteId) {
        model.addAttribute("note",noteRepo.findNoteById(noteId));
        return "user/self-note-view";
    }
}
