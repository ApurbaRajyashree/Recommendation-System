package com.example.nrs.controller;

import com.example.nrs.dto.NoteDto;
import com.example.nrs.dto.TeacherApprovalProcessDto;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.TeacherApprovalProcessService;
import jakarta.validation.Valid;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class TeacherApprovalController {

    private final TeacherApprovalProcessService teacherApprovalProcessService;
    private final UserRepo userRepo;

    public TeacherApprovalController(TeacherApprovalProcessService teacherApprovalProcessService, UserRepo userRepo) {
        this.teacherApprovalProcessService = teacherApprovalProcessService;
        this.userRepo = userRepo;
    }


    @GetMapping("/user/teacher-approval-request")
    public String teacherApprovalRequest(Model model, Principal principal) {
        model.addAttribute("user", userRepo.findByUserEmail(principal.getName()));
        model.addAttribute("teacherApprovalRequest", new TeacherApprovalProcessDto());
        return "user/teacher-approval-request";
    }

    @PostMapping("/user/teacher-approval-request/post")
    public String createNote(@Valid @ModelAttribute("teacher-approval-request") TeacherApprovalProcessDto teacherApprovalProcessDto,
                             BindingResult result, Principal principal,
                             RedirectAttributes redirectAttributes) {

        String success_message = "";
        teacherApprovalProcessDto.setUser(userRepo.findByUserEmail(principal.getName()));
        try {
            success_message = teacherApprovalProcessService.createApprovalRequest(teacherApprovalProcessDto);

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/user/teacher-approval-request";
        }
        redirectAttributes.addFlashAttribute("success_message", success_message);
        return "redirect:/user/teacher-approval-request";

    }

}
