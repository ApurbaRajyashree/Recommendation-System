package com.example.nrs.service.serviceImpl;

import com.example.nrs.component.FileStoreUtils;
import com.example.nrs.dto.TeacherApprovalProcessDto;
import com.example.nrs.entity.Role;
import com.example.nrs.entity.Status;
import com.example.nrs.entity.TeacherApprovalProcess;
import com.example.nrs.entity.User;
import com.example.nrs.repository.TeacherApprovalProcessRepo;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.TeacherApprovalProcessService;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherApprovalProcessServiceImpl implements TeacherApprovalProcessService {

    private final TeacherApprovalProcessRepo teacherApprovalProcessRepo;
    private final FileStoreUtils fileStoreUtils;


    private final UserRepo userRepo;

    public TeacherApprovalProcessServiceImpl(TeacherApprovalProcessRepo teacherApprovalProcessRepo, FileStoreUtils fileStoreUtils, UserRepo userRepo) {
        this.teacherApprovalProcessRepo = teacherApprovalProcessRepo;
        this.fileStoreUtils = fileStoreUtils;
        this.userRepo = userRepo;
    }

    @Override
    public String createApprovalRequest(TeacherApprovalProcessDto teacherApprovalProcessDto) throws TikaException, IOException {

        if(teacherApprovalProcessDto.getTeacherExperience()==null || teacherApprovalProcessDto.getEducationQualification()==null){
            throw new RuntimeException("Experience and Qualification should be selected");
        }
       TeacherApprovalProcess teacherApprovalProcess=new TeacherApprovalProcess(teacherApprovalProcessDto);
       teacherApprovalProcess.setDate(new Date());
       teacherApprovalProcess.setStatus(Status.SUBMITTED);
       teacherApprovalProcess.setFilePath(fileStoreUtils.saveMultipartFile(teacherApprovalProcessDto.getMultipartFile()));
       teacherApprovalProcessRepo.save(teacherApprovalProcess);
       return "Approval Request Sent Successfully \n Wait for a response from our administration";
    }

    @Override
    public List<TeacherApprovalProcessDto> getAllApprovalRequest() {
      List<TeacherApprovalProcess> teacherApprovalProcessList=teacherApprovalProcessRepo.findAll();
      return teacherApprovalProcessList.stream().map(x->new TeacherApprovalProcessDto(x)).collect(Collectors.toList());
    }

    @Override
    public List<TeacherApprovalProcessDto> getApprovalRequestByStatus(Status status) {
        List<TeacherApprovalProcess> teacherApprovalProcessList=teacherApprovalProcessRepo.findAllByStatus(status);
        return teacherApprovalProcessList.stream().map(x-> new TeacherApprovalProcessDto(x)).collect(Collectors.toList());
    }

    @Override
    public String deleteApprovalRequest(Integer id) {
        teacherApprovalProcessRepo.deleteById(id);
        return "Deleted Successfully";
    }


    public String approveTeacher(Integer id){
        Optional<TeacherApprovalProcess> teacherApprovalProcess=teacherApprovalProcessRepo.findById(id);
        if(teacherApprovalProcess.isPresent()){
            TeacherApprovalProcess teacherApprove=teacherApprovalProcess.get();
            teacherApprove.setStatus(Status.APPROVED);
            User user=teacherApprove.getUser();
            user.setRole(Role.TEACHER);
            teacherApprovalProcessRepo.save(teacherApprove);
            userRepo.save(user);
        }else {
            throw new RuntimeException("Approval Request does not exist!");
        }
        return "Teacher Approved!!!";
    }

    @Override
    public String rejectTeacher(Integer id) {
        Optional<TeacherApprovalProcess> teacherApprovalProcess=teacherApprovalProcessRepo.findById(id);
        if(teacherApprovalProcess.isPresent()){
            TeacherApprovalProcess teacherApprove=teacherApprovalProcess.get();
            teacherApprove.setStatus(Status.REJECTED);
            teacherApprovalProcessRepo.save(teacherApprove);
        }else {
            throw new RuntimeException("Approval Request does not exist!");
        }
        return "Teacher Rejected!!!";
    }
}
