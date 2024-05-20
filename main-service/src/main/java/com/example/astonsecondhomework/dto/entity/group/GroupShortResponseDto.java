package com.example.astonsecondhomework.dto.entity.group;

import lombok.Value;

@Value
public class GroupShortResponseDto {
    long id;
    String name;
    int course;
    int semester;
}
