package com.example.nrs.controller.User;

import com.example.nrs.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class DashboardController {
    public final DepartmentService departmentService;

    public DashboardController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {

        model.addAttribute("departments",departmentService);
        return "user/dashboard";
    }
}
