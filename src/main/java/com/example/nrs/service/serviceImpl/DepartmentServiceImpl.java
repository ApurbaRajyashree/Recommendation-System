package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.DepartmentDto;
import com.example.nrs.entity.Department;
import com.example.nrs.repository.DepartmentRepo;
import com.example.nrs.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    public final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = new Department(departmentDto);
        List<Department> departments=departmentRepo.findAll();
        for (Department eachDepartment:departments){
            if(eachDepartment.getDepartmentName().equalsIgnoreCase(department.getDepartmentName())){
                throw new RuntimeException("Department Already Exist");
            }
        }
        departmentRepo.save(department);
        return new DepartmentDto(department);

    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departments = departmentRepo.findAll();
        return departments.stream().map(DepartmentDto::new).collect(Collectors.toList());
    }
}
