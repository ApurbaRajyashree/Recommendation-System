package com.example.nrs.controller.User;


import com.example.nrs.dto.RatingDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.User;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class RatingController {

    private final RatingService ratingService;

    private final NoteRepo noteRepo;
    private final UserRepo userRepo;

    public RatingController(RatingService ratingService, NoteRepo noteRepo, UserRepo userRepo) {
        this.ratingService = ratingService;
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }


    @PostMapping("/user/note-view/{noteId}/rate")
    public String createRating(@Valid @ModelAttribute("rating")RatingDto ratingDto,
                                   @PathVariable("noteId")Integer noteId, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes, Principal principal) {

        if (result.hasErrors()) {
            model.addAttribute("rating", ratingDto);
            return "user/note-view";
        }
        Note note=noteRepo.findById(noteId).orElseThrow(()->
                new RuntimeException("Note does not exist"));

        try {
            User user=userRepo.findByUserEmail(principal.getName());
            ratingDto.setUser(user);

            ratingDto.setNote(note);
            ratingService.createRating(ratingDto);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());

            return "redirect:/user/note-view/"+note.getId();
        }
        return "redirect:/user/note-view/"+note.getId();
    }
}
