package com.example.nrs.controller.User;

import com.example.nrs.dto.RatingDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Rating;
import com.example.nrs.entity.User;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.RatingRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.NoteService;
import com.example.nrs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class NoteViewController {

    private final NoteService noteService;
    private final UserService userService;

    private final NoteRepo noteRepo;

    private final RatingRepo ratingRepo;

    private final UserRepo userRepo;

    public NoteViewController(NoteService noteService, UserService userService, NoteRepo noteRepo, RatingRepo ratingRepo, UserRepo userRepo) {
        this.noteService = noteService;
        this.userService = userService;
        this.noteRepo = noteRepo;
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/user/note-view/{noteId}")
    public String noteView(Model model, @PathVariable("noteId") Integer noteId, Principal principal) {
        model.addAttribute("note", noteRepo.findNoteById(noteId));
        model.addAttribute("rating", new RatingDto());
        User user=userRepo.findByUserEmail(principal.getName());
        Note note=noteRepo.findById(noteId).orElseThrow(()-> new RuntimeException("Note does not exist"));
        Rating rating=ratingRepo.findByUserAndNote(user,note);
        model.addAttribute("ratingCheck",rating==null? new RatingDto():rating );
        return "user/note-view";
    }

    @GetMapping("/user/self-note-view/{noteId}")
    public String selfNoteView(Model model, @PathVariable("noteId") Integer noteId) {
        model.addAttribute("note", noteRepo.findNoteById(noteId));
        return "user/self-note-view";
    }
}
