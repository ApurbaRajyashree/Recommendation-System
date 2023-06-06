package com.example.nrs.controller.Admin;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DepartmentController {

    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/department" , method = RequestMethod.GET)
    public String department(Model model) {
        model.addAttribute("department", new DepartmentDto());
        List<DepartmentDto> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        return "admin/department";
    }

    @RequestMapping(value = "/department/create",method = RequestMethod.POST)
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

            return "redirect:/department?fail";
        }
        return "redirect:/department?success";
    }
}
