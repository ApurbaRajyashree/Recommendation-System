package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.CourseDto;
import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.entity.Course;
import com.example.nrs.entity.Department;
import com.example.nrs.entity.SemesterName;
import com.example.nrs.repository.CourseRepo;
import com.example.nrs.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    public final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public String createCourse(CourseDto courseDto) {
        Course course = new Course(courseDto);
        List<Course> courses=courseRepo.findAllBySemester_Id(courseDto.getSemester().getId());
        for (Course eachCourse:courses){
            if(eachCourse.getCourseName().equalsIgnoreCase(course.getCourseName())){
                throw new RuntimeException(course.getCourseName()+" already exist in "+courseDto.getSemester().getSemesterName());
            }
        }
        courseRepo.save(course);
        return "Course "+course.getCourseName()+" created successfully";
    }

    @Override
    public List<CourseDto> getAllCourses() {
       List<Course> courses= courseRepo.findAll();
        return courses.stream().map(x->new CourseDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> findCourseBySemesterName(SemesterName semesterName) {
       List<Course> courses=courseRepo.findAllBySemester_SemesterName(semesterName);
        return courses.stream().map(x->new CourseDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> findAllCourseBySemesterId(Integer id) {
        List<Course> courses=courseRepo.findAllBySemester_Id(id);
        return courses.stream().map(x->new CourseDto(x)).collect(Collectors.toList());

    }

    @Override
    public String updateCourse(Integer id, CourseDto courseDto) {
        Optional<Course> optionalCourse=courseRepo.findById(id);
        if(optionalCourse.isPresent()){
            Course course=optionalCourse.get();
            course.setCourseName(courseDto.getCourseName());
            course.setCourseDescription(courseDto.getCourseDescription());
            course.setSemester(courseDto.getSemester());
            courseRepo.save(course);
        }else {
            throw new RuntimeException("Course does not exist");
        }
        return "Course updated successfully";
    }

    @Override
    public String deleteCourse(Integer id) {
        courseRepo.deleteById(id);
        return "Successfully deleted";
    }
}
