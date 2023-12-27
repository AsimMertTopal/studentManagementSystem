package com.AsimMertTopal.studentManagementSystem.entities;

import com.AsimMertTopal.studentManagementSystem.enums.LessonName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer id;

    @Column(name="lesson")
    @Enumerated(EnumType.STRING)
    private LessonName lesson;

    @ManyToMany(mappedBy = "lessons")
    private List<Student> students;


    public Teacher getTeacher() {
        return teacher;
    }

    @OneToOne
    private Teacher teacher;
}