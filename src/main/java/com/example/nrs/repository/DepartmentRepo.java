package com.example.nrs.repository;

import com.example.nrs.entity.Department;
import com.example.nrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
    Department findByDepartmentName(String name);

    @Query(value = "select full_name from users,department where users.department_id=department.id AND\n" +
            "                                             department_id=?1 ",nativeQuery = true)
    List<User> findAllUserByDepartmentId(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Department  SET is_active = false  WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
