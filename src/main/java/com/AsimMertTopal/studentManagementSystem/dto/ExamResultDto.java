package com.AsimMertTopal.studentManagementSystem.dto;

import com.AsimMertTopal.studentManagementSystem.enums.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultDto {
    private Integer studentId;
    private Integer examResult;
    private Branch teacherBranch;

}
