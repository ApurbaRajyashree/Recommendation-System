package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.TeacherApprovalProcessDto;
import com.example.nrs.entity.Status;
import com.example.nrs.entity.TeacherApprovalProcess;
import com.example.nrs.repository.TeacherApprovalProcessRepo;
import com.example.nrs.service.TeacherApprovalProcessService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherApprovalProcessServiceImpl implements TeacherApprovalProcessService {

    private final TeacherApprovalProcessRepo teacherApprovalProcessRepo;

    public TeacherApprovalProcessServiceImpl(TeacherApprovalProcessRepo teacherApprovalProcessRepo) {
        this.teacherApprovalProcessRepo = teacherApprovalProcessRepo;
    }

    @Override
    public String createApprovalRequest(TeacherApprovalProcessDto teacherApprovalProcessDto) {
       TeacherApprovalProcess teacherApprovalProcess=new TeacherApprovalProcess(teacherApprovalProcessDto);
       teacherApprovalProcess.setDate(new Date());
       teacherApprovalProcess.setStatus(Status.SUBMITTED);
       teacherApprovalProcessRepo.save(teacherApprovalProcess);
       return "Approval Request Saved Successfully";
    }

    @Override
    public List<TeacherApprovalProcessDto> getAllApprovalRequest() {
      List<TeacherApprovalProcess> teacherApprovalProcessList=teacherApprovalProcessRepo.findAll();
      return teacherApprovalProcessList.stream().map(x->new TeacherApprovalProcessDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<TeacherApprovalProcessDto> getApprovalRequestByStatus(String status) {
        List<TeacherApprovalProcess> teacherApprovalProcessList=teacherApprovalProcessRepo.findAllByStatus(status);
        return teacherApprovalProcessList.stream().map(x-> new TeacherApprovalProcessDto(x)).collect(Collectors.toList());
    }

    @Override
    public String deleteApprovalRequest(Integer id) {
        teacherApprovalProcessRepo.deleteById(id);
        return "Deleted Successfully";
    }
}
