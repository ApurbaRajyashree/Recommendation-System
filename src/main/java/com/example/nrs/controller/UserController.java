package com.example.nrs.controller;

import com.example.nrs.dto.UserDto;
import com.example.nrs.service.DepartmentService;
import com.example.nrs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final DepartmentService departmentService;

    public UserController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("departments", departmentService.getAllDepartment());
        return "register";
    }

    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            Map<String, String> requestErrors = validateRequest(result);
            redirectAttributes.addFlashAttribute("requestError", requestErrors);

            return "redirect:/register";
        }
        try {
            userService.createUser(userDto);

        } catch (Exception e) {
            String msg = "";
            if (e.getMessage().contains("uk_mobile_number")) {
                msg += "Sorry Mobile Number already Exist.\n";
            } else if (e.getMessage().contains("uk_email")) {
                msg += "Sorry Email already exists. \n";
            }else{
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/register?fail";
            }
            redirectAttributes.addFlashAttribute("error", msg);

            return "redirect:/register?fail";
        }
        return "redirect:/register?success";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }
}
