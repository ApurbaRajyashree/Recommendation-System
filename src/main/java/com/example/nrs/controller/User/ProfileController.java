package com.example.nrs.controller.User;


import com.example.nrs.dto.RatingDto;
import com.example.nrs.entity.Note;
import com.example.nrs.entity.Rating;
import com.example.nrs.entity.User;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ProfileController {

    private final UserService userService;
    private final UserRepo userRepo;

    public ProfileController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/user/profile")
    public String noteView(Model model, Principal principal) {
        User user=userRepo.findByUserEmail(principal.getName());
        model.addAttribute("user",user);
        return "user/profile";
    }
}
