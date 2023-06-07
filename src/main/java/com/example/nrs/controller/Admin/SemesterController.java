package com.example.nrs.controller.Admin;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.dto.SemesterDto;
import com.example.nrs.dto.SemesterRequestDto;
import com.example.nrs.service.DepartmentService;
import com.example.nrs.service.SemesterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SemesterController {
    public final SemesterService semesterService;

    public final DepartmentService departmentService;

    public SemesterController(SemesterService semesterService, DepartmentService departmentService) {
        this.semesterService = semesterService;
        this.departmentService = departmentService;
    }


    @GetMapping("/admin/semester")
    public String semester(Model model) {
        model.addAttribute("semester", new SemesterDto());
        model.addAttribute("semesterRequest",new SemesterRequestDto());
        List<SemesterDto> allSemester = semesterService.getAllSemester();
        model.addAttribute("semesters", allSemester);
        model.addAttribute("departments",departmentService.getAllDepartment());
        return "admin/semester";
    }

    @PostMapping("/admin/semester/create")
    public String createSemester(@Valid @ModelAttribute("semester") SemesterRequestDto semesterRequestDto, BindingResult result, Model model,
                                 RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("semester", semesterRequestDto);
            return "admin/semester";
        }
        String msg="";
        try {
           msg= semesterService.createSemesters(semesterRequestDto);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/semester?fail";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/admin/semester?success";
    }

    @RequestMapping(value = "/admin/semester/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@ModelAttribute("semester") SemesterDto semesterDto,
                                @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        String msg;
        try {
            msg=semesterService.deleteSemester(id);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/semester?fail";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/admin/semester?success";    }
}
