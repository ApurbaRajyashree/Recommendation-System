package com.example.nrs.controller.User;

import com.example.nrs.dto.CourseDto;
import com.example.nrs.dto.SemesterDto;
import com.example.nrs.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserCourseController {

    private final CourseService courseService;

    public UserCourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/user/semester/{semesterId}/course")
    public String course(Model model, @PathVariable("semesterId") Integer semesterId) {
        model.addAttribute("course", new CourseDto());
        List<CourseDto> courseDtos=courseService.findAllCourseBySemesterId(semesterId);
        model.addAttribute("courses", courseDtos);
        return "user/course-by-semester";
    }

    @GetMapping("/user/course")
    public String allCourse(Model model) {
        model.addAttribute("course", new CourseDto());
        List<CourseDto> courseDtos=courseService.getAllCourses();
        model.addAttribute("courses", courseDtos);
        return "user/course";
    }

}
