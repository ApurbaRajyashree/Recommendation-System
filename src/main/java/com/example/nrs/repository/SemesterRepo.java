package com.example.nrs.repository;

import com.example.nrs.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SemesterRepo extends JpaRepository<Semester,Integer> {
    List<Semester> findSemestersByDepartment_Id(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Semester  SET is_active = false  WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);

}
