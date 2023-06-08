package com.example.nrs.controller.User;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.dto.SemesterDto;
import com.example.nrs.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserDepartmentController {
    private final DepartmentService departmentService;

    public UserDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/user/department")
    public String department(Model model) {
        model.addAttribute("department", new DepartmentDto());
        List<DepartmentDto> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        return "user/department";
    }


}
