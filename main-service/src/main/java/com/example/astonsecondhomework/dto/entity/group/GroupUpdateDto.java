package com.example.astonsecondhomework.dto.entity.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GroupUpdateDto {
    @Setter
    long id;
    String name;
    int course;
    int semester;
}
