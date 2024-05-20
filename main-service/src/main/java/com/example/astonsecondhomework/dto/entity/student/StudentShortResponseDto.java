package com.example.astonsecondhomework.dto.entity.student;

import lombok.Value;

@Value
public class StudentShortResponseDto {
    long id;
    String lastName;
    String firstName;
    String patronymic;
}
