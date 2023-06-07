package com.example.nrs.repository;

import com.example.nrs.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepo extends JpaRepository<Semester,Integer> {
    List<Semester> findSemestersByDepartment_Id(Integer id);

    @Query(value = "UPDATE semester s SET s.is_active=false where s.id=?1", nativeQuery = true)
    void deleteById(Integer integer);

}
