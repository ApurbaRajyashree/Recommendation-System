package com.example.nrs.controller.User;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.dto.SemesterDto;
import com.example.nrs.service.SemesterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserSemesterController {

    private  final SemesterService semesterService;

    public UserSemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping("/user/department/{departmentId}/semester")
    public String semester(Model model, @PathVariable("departmentId") Integer departmentId) {
        model.addAttribute("semester", new SemesterDto());
        List<SemesterDto> semesterDtos=semesterService.getSemestersByDepartment(departmentId);
        model.addAttribute("semesters", semesterDtos);
        return "user/semester";
    }
}
