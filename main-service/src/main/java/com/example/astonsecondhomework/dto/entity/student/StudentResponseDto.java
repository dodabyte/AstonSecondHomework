package com.example.astonsecondhomework.dto.entity.student;

import com.example.astonsecondhomework.dto.entity.group.GroupShortResponseDto;
import lombok.Value;

@Value
public class StudentResponseDto {
    long id;
    String lastName;
    String firstName;
    String patronymic;
    GroupShortResponseDto group;
}
