package com.example.astonsecondhomework.dto.entity.group;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GroupRequestDto {
    String name;
    int course;
    int semester;
}
