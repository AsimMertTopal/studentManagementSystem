package com.AsimMertTopal.studentManagementSystem.entities;

import com.AsimMertTopal.studentManagementSystem.enums.LessonName;
import com.AsimMertTopal.studentManagementSystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@PreAuthorize("hasRole('STUDENT')")
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_password")
    private String password;

    @Column(name = "discontinuity")
    private Integer discontinuity;

    @Column(name = "schoolClass")
    private String schoolClass;

    @Column(name = "syllabus")
    private String syllabus;

    @Column(name = "examResults")
    private Integer examResults;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Column(name="lesson")
//    @Enumerated(EnumType.STRING)
//    private Lesson lesson;


    @ManyToMany
    @JoinTable(
            name = "student_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lesson> lessons;

    @Column(name = "selected_lessons")
    private List<LessonName> selectedLessons;

    public void addLesson(LessonName lesson) {
        if (selectedLessons == null) {
            selectedLessons = new ArrayList<>();
        }
        selectedLessons.add(lesson);
    }



    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
