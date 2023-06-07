package com.example.nrs.controller.Admin;

import com.example.nrs.dto.SemesterDto;
import com.example.nrs.service.SemesterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SemesterController {
    public final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }


    @GetMapping("/admin/semester")
    public String semester(Model model) {
        model.addAttribute("semester", new SemesterDto());
        List<SemesterDto> allSemester = semesterService.getAllSemester();
        model.addAttribute("semesters", allSemester);
        return "admin/semester";
    }

    @PostMapping("admin/semester/create")
    public String createSemester(@Valid @ModelAttribute("semester") SemesterDto semesterDto, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("department", semesterDto);
            return "admin/semester";
        }
        try {
            semesterService.createSemester(semesterDto);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/semester/?fail";
        }
        return "redirect:/admin/semester/?success";
    }
}
