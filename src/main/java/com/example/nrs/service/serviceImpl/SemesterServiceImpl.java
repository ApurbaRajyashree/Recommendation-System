package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.dto.SemesterDto;
import com.example.nrs.dto.SemesterRequestDto;
import com.example.nrs.entity.Department;
import com.example.nrs.entity.Semester;
import com.example.nrs.entity.SemesterName;
import com.example.nrs.repository.SemesterRepo;
import com.example.nrs.service.SemesterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    public final SemesterRepo semesterRepo;

    public SemesterServiceImpl(SemesterRepo semesterRepo) {
        this.semesterRepo = semesterRepo;
    }

    @Override
    public SemesterDto createSemester(SemesterDto semesterDto) {
        Semester semester = new Semester(semesterDto);
        List<Semester> semesters = semesterRepo.findAll();
        for (Semester eachSemester : semesters) {
            if (eachSemester.getDepartment().getId().equals(semester.getDepartment().getId())) {
                if (eachSemester.getSemesterName().equals(semester.getSemesterName())) {
                    throw new RuntimeException(eachSemester.getSemesterName() +
                            " already exist in department " + eachSemester.getDepartment().getDepartmentName());
                }
            }
        }
        semesterRepo.save(semester);
        return new SemesterDto(semester);
    }

    @Override
    public List<SemesterDto> createSemesters(SemesterRequestDto semesterRequestDto) {
        List<SemesterName> semesterNameList=semesterRequestDto.getSemesterNames();
        List<Semester> semesters=semesterRepo.findSemestersByDepartment_Id(semesterRequestDto.getDepartment().getId());
        List<Semester> newSemesters=new ArrayList<>();
        for (Semester eachSemester:semesters){
            for (SemesterName eachSemesterName:semesterNameList){
                if(eachSemesterName.equals(eachSemester.getSemesterName())){
                    throw new RuntimeException(eachSemester.getSemesterName() +
                            " already exist in department " + eachSemester.getDepartment().getDepartmentName());
                }else {
                    Semester semester=new Semester();
                    semester.setSemesterName(eachSemesterName);
                    semester.setDepartment(eachSemester.getDepartment());
                    semester.setActive(true);
                    newSemesters.add(semester);
                }
            }
        }

        semesterRepo.saveAll(newSemesters);
        return newSemesters.stream().map(x-> new SemesterDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<SemesterDto> getAllSemester() {
        List<Semester> semesters = semesterRepo.findAll();
        return semesters.stream().map(SemesterDto::new).collect(Collectors.toList());
    }

    @Override
    public List<SemesterDto> getSemestersByDepartment(Integer id) {
        List<Semester> semesters=semesterRepo.findSemestersByDepartment_Id(id);
        if (semesters != null) {
            return semesters.stream().map(SemesterDto::new).collect(Collectors.toList());
        } else
            throw new RuntimeException("Department does not have any semester");
    }

    @Override
    public String deleteSemester(Integer id) {
        semesterRepo.deleteById(id);
        return "Successfully deleted";
    }
}
