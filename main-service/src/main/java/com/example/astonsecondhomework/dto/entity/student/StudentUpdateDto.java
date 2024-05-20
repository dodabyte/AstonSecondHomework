package com.example.astonsecondhomework.dto.entity.student;

import com.example.astonsecondhomework.dto.entity.group.GroupShortUpdateDto;
import com.example.astonsecondhomework.dto.entity.group.GroupUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class StudentUpdateDto {
    @Setter
    long id;
    String lastName;
    String firstName;
    String patronymic;
    GroupShortUpdateDto group;
}
