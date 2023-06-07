package com.example.nrs.repository;

import com.example.nrs.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepo extends JpaRepository<Semester,Integer> {
    List<Semester> findSemestersByDepartment_Id(Integer id);
}
