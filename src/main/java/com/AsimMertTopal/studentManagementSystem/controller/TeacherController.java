package com.AsimMertTopal.studentManagementSystem.controller;

import com.AsimMertTopal.studentManagementSystem.dto.ExamResultDto;

import com.AsimMertTopal.studentManagementSystem.dto.JwtAuthenticationResponse;
import com.AsimMertTopal.studentManagementSystem.dto.SignUpRequest;
import com.AsimMertTopal.studentManagementSystem.entities.Teacher;
import com.AsimMertTopal.studentManagementSystem.enums.Branch;
import com.AsimMertTopal.studentManagementSystem.security.AuthenticationService;
import com.AsimMertTopal.studentManagementSystem.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
@NoArgsConstructor
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    private AuthenticationService authenticationService;



    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody Teacher teacher) {
        teacherService.registerTeacher(teacher);
        return ResponseEntity.ok("Öğretmen Oluşturuldu");
    }

    @PostMapping("/createTeacher")
    public ResponseEntity<JwtAuthenticationResponse> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(authenticationService.createTeacher(teacher));

    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(authenticationService.logInTeacher(teacher));
    }

    @PostMapping("/addExamResult")
    @PreAuthorize("hasRole('TEACHER')")

    public void enterExamResult(@RequestBody ExamResultDto examResultRequest) {
        teacherService.enterExamResult(examResultRequest.getStudentId(), examResultRequest.getExamResult(), examResultRequest.getTeacherBranch());
    }

    @PostMapping("/addTeacherBranch")
    @PreAuthorize("hasRole('TEACHER')")

    public void createTeacherWithBranch(@RequestBody Branch branch){
        teacherService.createTeacherWithBranch(branch);
    }
    @GetMapping("/all")
    public List<Teacher>findAll(){
        return teacherService.findAll();
    }
}


