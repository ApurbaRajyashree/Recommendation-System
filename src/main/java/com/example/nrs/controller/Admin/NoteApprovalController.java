package com.example.nrs.controller.Admin;

import com.example.nrs.dto.NoteDto;
import com.example.nrs.dto.TeacherApprovalProcessDto;
import com.example.nrs.entity.Status;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class NoteApprovalController {

    private final NoteService noteService;
    private final NoteRepo noteRepo;
    private final UserRepo userRepo;

    public NoteApprovalController(NoteService noteService, NoteRepo noteRepo, UserRepo userRepo) {
        this.noteService = noteService;
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/admin/note-approval-submitted")
    public String noteApprovalSubmitted(Model model, Principal principal) {
        model.addAttribute("user", userRepo.findByUserEmail(principal.getName()));
        model.addAttribute("noteApprovalRequest", new NoteDto());
        model.addAttribute("submittedRequest",noteService.getAllNotesByStatus(Status.SUBMITTED));
        return "admin/note-approval-submitted";
    }

    @GetMapping("/admin/note-approved")
    public String teacherApprovalAccept(Model model, Principal principal) {
        model.addAttribute("user", userRepo.findByUserEmail(principal.getName()));
        model.addAttribute("noteApprovalRequest", new NoteDto());
        model.addAttribute("submittedRequest",noteService.getAllNotesByStatus(Status.APPROVED));

        return "admin/note-approval-approved";
    }


    @GetMapping("/admin/note-rejected")
    public String teacherApprovalReject(Model model, Principal principal) {
        model.addAttribute("user", userRepo.findByUserEmail(principal.getName()));
        model.addAttribute("noteApprovalRequest", new NoteDto());
        model.addAttribute("submittedRequest",noteService.getAllNotesByStatus(Status.REJECTED));

        return "admin/note-approval-rejected";
    }

    @GetMapping("/admin/note-approve/{noteId}")
    public String teacherApprove(@PathVariable("noteId")Integer noteId, @ModelAttribute("note-approve") NoteDto noteDto, BindingResult result, Model model,
                                 RedirectAttributes redirectAttributes) {

        String msg="";
        try {
            msg= noteService.acceptNote(noteId);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/note-approval-submitted?fail";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/admin/note-approval-submitted?success";
    }


    @GetMapping("/admin/note-reject/{noteId}")
    public String teacherReject(@PathVariable("noteId")Integer noteId,@ModelAttribute("note-reject") NoteDto noteDto, BindingResult result, Model model,
                                RedirectAttributes redirectAttributes) {

        String msg="";
        try {
            msg= noteService.rejectNote(noteId);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/note-approval-submitted?fail";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/admin/note-approval-submitted?success";
    }
}
