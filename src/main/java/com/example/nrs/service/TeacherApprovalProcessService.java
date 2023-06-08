package com.example.nrs.service;

import com.example.nrs.dto.TeacherApprovalProcessDto;
import com.example.nrs.entity.TeacherApprovalProcess;

import java.util.List;

public interface TeacherApprovalProcessService {

    String createApprovalRequest(TeacherApprovalProcessDto teacherApprovalProcessDto);

    List<TeacherApprovalProcessDto> getAllApprovalRequest();

    List<TeacherApprovalProcessDto> getApprovalRequestByStatus(String status);

    String deleteApprovalRequest(Integer id);
}
