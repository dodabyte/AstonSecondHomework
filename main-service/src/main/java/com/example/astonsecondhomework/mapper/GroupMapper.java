package com.example.astonsecondhomework.mapper;

import com.example.astonsecondhomework.dto.entity.group.*;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(target = "name", source = "entityDto.name")
    @Mapping(target = "course", source = "entityDto.course")
    @Mapping(target = "semester", source = "entityDto.semester")
    Group map(GroupRequestDto entityDto);

    @Mapping(target = "id", source = "entityDto.id")
    Group map(GroupShortRequestDto entityDto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "course", source = "entity.course")
    @Mapping(target = "semester", source = "entity.semester")
    @Mapping(target = "students", source = "entity.students")
    GroupResponseDto map(Group entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "course", source = "entity.course")
    @Mapping(target = "semester", source = "entity.semester")
    GroupShortResponseDto shortMap(Group entity);

    @Mapping(target = "id", source = "entityDto.id")
    @Mapping(target = "name", source = "entityDto.name")
    @Mapping(target = "course", source = "entityDto.course")
    @Mapping(target = "semester", source = "entityDto.semester")
    Group map(GroupUpdateDto entityDto);

    @Mapping(target = "id", source = "entityDto.id")
    Group map(GroupShortUpdateDto entityDto);

    List<GroupResponseDto> map(List<Group> entities);
}
