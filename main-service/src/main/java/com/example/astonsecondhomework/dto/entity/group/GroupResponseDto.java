package com.example.astonsecondhomework.dto.entity.group;

import com.example.astonsecondhomework.dto.entity.student.StudentShortResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
public class GroupResponseDto {
    long id;
    String name;
    int course;
    int semester;
    List<StudentShortResponseDto> students;
}
