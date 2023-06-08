package com.example.nrs.controller.User;

import com.example.nrs.component.FileStoreUtils;
import com.example.nrs.dto.CourseDto;
import com.example.nrs.dto.NoteDto;
import com.example.nrs.entity.Status;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.CourseService;
import com.example.nrs.service.NoteService;
import jakarta.validation.Valid;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class UserNoteController {
    private final FileStoreUtils fileStoreUtils;

    private final NoteService noteService;

    private final UserRepo userRepo;

    private final CourseService courseService;

    private final NoteRepo noteRepo;

    public UserNoteController(FileStoreUtils fileStoreUtils, NoteService noteService, UserRepo userRepo, CourseService courseService, NoteRepo noteRepo) {
        this.fileStoreUtils = fileStoreUtils;
        this.noteService = noteService;
        this.userRepo = userRepo;
        this.courseService = courseService;
        this.noteRepo = noteRepo;
    }

    @GetMapping("/user/note")
    public String note(Model model, Principal principal) {
        model.addAttribute("user", userRepo.findByUserEmail(principal.getName()));
        model.addAttribute("note", new NoteDto());
        List<NoteDto> noteDtos = noteService.getAllNotesByUserEmail(principal.getName());
        model.addAttribute("noteDtos", noteRepo.findAll());
        model.addAttribute("courses", courseService.getAllCourses());
        return "user/note";
    }

    @PostMapping("/user/note/post")
    public String createNote(@Valid @ModelAttribute("note") NoteDto noteDto, BindingResult result, Principal principal,
                             RedirectAttributes redirectAttributes) throws IOException, TikaException {

        String type = fileStoreUtils.extensionvalidation(noteDto.getMultipartFile());
        String success_message = "";
        if (type.equals("application/pdf")) {
            noteDto.setUser(userRepo.findByUserEmail(principal.getName()));
            try {
                noteService.createNote(noteDto);

            } catch (RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
                return "redirect:/user/note";

            }

            if (noteDto.getId() == null) {
                success_message = "Note Created Successfully";
            } else {
                success_message = "Note updated Successfully!!";
            }
            redirectAttributes.addFlashAttribute("success_message", success_message);
        } else {
            String message = "Failed! File type should be pdf";
            redirectAttributes.addFlashAttribute("message", message);
        }
        return "redirect:/user/note";
    }


    @GetMapping("/user/course/{courseId}/note")
    public String course(Model model, @PathVariable("courseId") Integer courseId) {
        model.addAttribute("note", new NoteDto());
        List<NoteDto> noteDtos = noteService.getAllNotesByCourseIdAndStatus(courseId,"APPROVED");
        model.addAttribute("notes", noteDtos);
        return "user/note-by-course";
    }


    @GetMapping("/user/note/all-approved")
    public String course(Model model) {
        model.addAttribute("note", new NoteDto());
        List<NoteDto> noteDtos = noteService.getAllNotesByStatus(Status.APPROVED);
        model.addAttribute("notes", noteDtos);
        return "user/all-notes";
    }
}
