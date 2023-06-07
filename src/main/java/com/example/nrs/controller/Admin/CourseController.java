package com.example.nrs.controller.Admin;

import com.example.nrs.dto.CourseDto;
import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.service.CourseService;
import com.example.nrs.service.SemesterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CourseController {
    public final CourseService courseService;
    public final SemesterService semesterService;

    public CourseController(CourseService courseService, SemesterService semesterService) {
        this.courseService = courseService;
        this.semesterService = semesterService;
    }

    @GetMapping("/admin/course")
    public String course(Model model) {
        model.addAttribute("course", new CourseDto());
        List<CourseDto> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("semesters",semesterService.getAllSemester());
        return "admin/course";
    }

    @PostMapping("/admin/course/create")
    public String createDepartment(@Valid @ModelAttribute("course") CourseDto courseDto, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("course", courseDto);
            return "admin/course";
        }
        String msg;
        try {
           msg= courseService.createCourse(courseDto);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/course?fail";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/admin/course?success";
    }

    @RequestMapping(value = "/admin/course/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@ModelAttribute("course") DepartmentDto courseDto,
                                @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        String msg="";
        try {
            msg=courseService.deleteCourse(id);
        }catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/course?fail";
        }
        redirectAttributes.addFlashAttribute("msg",msg);
        return "redirect:/admin/course?success";    }
}
