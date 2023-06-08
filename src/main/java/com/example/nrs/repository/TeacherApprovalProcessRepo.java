package com.example.nrs.repository;

import com.example.nrs.entity.TeacherApprovalProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherApprovalProcessRepo extends JpaRepository<TeacherApprovalProcess,Integer> {

    List<TeacherApprovalProcess> findAllByStatus(String status);
}
