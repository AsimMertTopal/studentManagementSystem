package com.AsimMertTopal.studentManagementSystem.controller;

import com.AsimMertTopal.studentManagementSystem.dto.ExamResultDto;
import com.AsimMertTopal.studentManagementSystem.dto.JwtAuthenticationResponse;
import com.AsimMertTopal.studentManagementSystem.dto.StudentRegistrationDto;
import com.AsimMertTopal.studentManagementSystem.entities.Student;
import com.AsimMertTopal.studentManagementSystem.enums.LessonName;
import com.AsimMertTopal.studentManagementSystem.security.AuthenticationService;
import com.AsimMertTopal.studentManagementSystem.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentController {
    private StudentService studentService;
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody StudentRegistrationDto student) {
        studentService.registerStudent(student);
        return ResponseEntity.ok("Öğrenci Oluşturuldu");
    }

    @PostMapping("/createStudent")
    public ResponseEntity<JwtAuthenticationResponse> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(authenticationService.createStudent(student));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody Student student) {
        return ResponseEntity.ok(authenticationService.logInStudent(student));
    }

    @PostMapping("/addLesson")
    @PreAuthorize("hasRole('STUDENT')")
    public void selectLesson (Integer studentId, LessonName... lessons){
        studentService.selectLessons(studentId,lessons);
    }

    @GetMapping("/getLessons")
    @PreAuthorize("hasRole('STUDENT')")
     public void getLessons(Integer studentId){
         studentService.getSelectedLessons(studentId);
        }

    @GetMapping("/students/{studentId}/examResults")
    @PreAuthorize("hasRole('STUDENT')")
    public List<ExamResultDto> getStudentExamResults(@PathVariable Integer studentId){
        return studentService.getStudentExamResults(studentId);
    }


    }

