package com.example.nrs.controller.Admin;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.dto.UserDto;
import com.example.nrs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AllUserController {

    private final UserService userService;

    public AllUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/user-list")
    public String department(Model model) {
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/user-list";
    }
}
