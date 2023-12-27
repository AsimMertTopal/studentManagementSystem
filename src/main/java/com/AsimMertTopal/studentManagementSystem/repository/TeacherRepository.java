package com.AsimMertTopal.studentManagementSystem.repository;

import com.AsimMertTopal.studentManagementSystem.entities.Teacher;
import com.AsimMertTopal.studentManagementSystem.enums.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    List<Teacher> findByBranch(Branch branch);

    Optional<Teacher> findByEmail(String email);
}
