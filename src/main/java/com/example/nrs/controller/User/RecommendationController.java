package com.example.nrs.controller.User;


import com.example.nrs.dto.NoteDto;
import com.example.nrs.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class RecommendationController {

    private final NoteService noteService;

    public RecommendationController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/user/recommendations")
    public String readRecommendation(Model model, Principal principal){

       List<NoteDto> noteDtos= noteService.recommendNoteForUser(principal, 6);
      model.addAttribute("recommendation",noteDtos);
      return "user/recommended";

    }
}
