package com.AsimMertTopal.studentManagementSystem.service;

import com.AsimMertTopal.studentManagementSystem.dto.ExamResultDto;
import com.AsimMertTopal.studentManagementSystem.dto.StudentRegistrationDto;
import com.AsimMertTopal.studentManagementSystem.entities.Lesson;
import com.AsimMertTopal.studentManagementSystem.entities.Student;
import com.AsimMertTopal.studentManagementSystem.enums.LessonName;
import com.AsimMertTopal.studentManagementSystem.repository.StudentRepository;
import com.AsimMertTopal.studentManagementSystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentService {
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Transactional
    //Securityden sonra buraya şifre eklenecek ve hashlenecek
    public void registerStudent(StudentRegistrationDto studentDTO) {
        Student student=new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        studentRepository.save(student);
    }

    @Transactional
    public void selectLessons(Integer studentId, LessonName... lessons) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isEmpty()) {
            throw new IllegalArgumentException("Öğrenci bulunamadı: " + studentId);
        }

        Student student = optionalStudent.get();

        if (lessons.length < 3){
            throw new IllegalArgumentException("En az 3 ders seçmelisiniz");
        }

        for (LessonName lesson : lessons) {
            student.addLesson(lesson);
        }

        studentRepository.save(student);
    }
    public Integer getExamResultForLesson(Student student, Lesson lesson) {
        // Öğrencinin derslerini içeren listeden ilgili derse ait sonucu almak için
        List<LessonName> studentLessons = student.getSelectedLessons();

        if (studentLessons.contains(lesson)) {

            return student.getExamResults();
        } else {

            return 0;
        }
    }




    @Transactional
    public List<ExamResultDto> getStudentExamResults(Integer studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<ExamResultDto> examResultDtoList = new ArrayList<>();

            for (Lesson lesson : student.getLessons()) {
                Integer result = getExamResultForLesson(student, lesson);
                ExamResultDto examResultDto = new ExamResultDto(studentId, result, lesson.getTeacher().getBranch());
                examResultDtoList.add(examResultDto);
            }

            return examResultDtoList;
        } else {
            throw new IllegalArgumentException("Öğrenci Bulunamadı.");
        }
    }


    public List<LessonName> getSelectedLessons(Integer studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return student.getSelectedLessons();
        } else {
            throw new IllegalArgumentException("Öğrenci Bulunamadı.");
        }
    }
}
