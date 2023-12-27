package com.AsimMertTopal.studentManagementSystem.repository;


import com.AsimMertTopal.studentManagementSystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student,Integer> {

    Optional<Student> findByEmail(String email);
}
