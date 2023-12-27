package com.AsimMertTopal.studentManagementSystem.service;


import com.AsimMertTopal.studentManagementSystem.entities.Student;
import com.AsimMertTopal.studentManagementSystem.entities.Teacher;
import com.AsimMertTopal.studentManagementSystem.enums.Branch;
import com.AsimMertTopal.studentManagementSystem.repository.StudentRepository;
import com.AsimMertTopal.studentManagementSystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    public List<Teacher> findAll(){
      return  teacherRepository.findAll();
    }

    @Transactional
    public void registerTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void createTeacherWithBranch(Branch branch) {
        Teacher teacher = new Teacher();
        teacher.setBranch(teacher.getBranch());
        teacherRepository.save(teacher);
    }

    public void enterExamResult(Integer studentId, Integer examResult, Branch teacherBranch) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // Öğrencinin bağlı olduğu tüm öğretmenleri al
            List<Teacher> teachers = student.getTeachers();

            // Öğrencinin bağlı olduğu ve istenen branşa sahip öğretmenleri filtrele
            List<Teacher> filteredTeachers = teachers.stream()
                    .filter(teacher -> teacherBranch.equals(teacher.getBranch()))
                    .collect(Collectors.toList());

            if (!filteredTeachers.isEmpty()) {
                // Filtrelenmiş öğretmen listesine ait öğrencilere not girme işlemi
                student.setExamResults(examResult);
                studentRepository.save(student);
            } else {
                throw new IllegalArgumentException("Bu öğretmen not  verebilecek yetkiye sahip değil.");
            }
        } else {
            throw new IllegalArgumentException("Öğrenci Bulunamadı veya dersi almıyor.");
        }
    }


}






