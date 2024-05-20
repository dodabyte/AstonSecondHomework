package com.example.astonsecondhomework.dto.entity.student;

import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class StudentRequestDto {
    String lastName;
    String firstName;
    String patronymic;
    GroupRequestDto group;
}
