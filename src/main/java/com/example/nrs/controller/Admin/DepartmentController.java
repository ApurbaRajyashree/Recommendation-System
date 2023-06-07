package com.example.nrs.controller.Admin;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class DepartmentController {

    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/admin/department")
    public String department(Model model) {
        model.addAttribute("department", new DepartmentDto());
        List<DepartmentDto> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        return "admin/department";
    }

    @PostMapping("/admin/department/create")
    public String createDepartment(@Valid @ModelAttribute("department") DepartmentDto departmentDto, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("department", departmentDto);
            return "admin/department";
        }
        try {
            departmentService.createDepartment(departmentDto);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());

            return "redirect:/admin/department?fail";
        }
        return "redirect:/admin/department?success";
    }

    @RequestMapping(value = "/admin/department/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@ModelAttribute("department") DepartmentDto departmentDto,
                                @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            departmentService.deleteDepartment(id);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/department?fail";
        }
        return "redirect:/admin/department?success";    }

}
