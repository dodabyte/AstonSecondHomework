package com.example.astonsecondhomework.mapper;

import com.example.astonsecondhomework.dto.entity.group.GroupShortResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentShortResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface StudentMapper {
    @Mapping(target = "lastName", source = "entityDto.lastName")
    @Mapping(target = "firstName", source = "entityDto.firstName")
    @Mapping(target = "patronymic", source = "entityDto.patronymic")
    @Mapping(target = "group", source = "entityDto.group")
    Student map(StudentRequestDto entityDto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "patronymic", source = "entity.patronymic")
    @Mapping(target = "group", source = "entity.group", resultType = GroupShortResponseDto.class)
    StudentResponseDto map(Student entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "patronymic", source = "entity.patronymic")
    StudentShortResponseDto shortMap(Student entity);

    @Mapping(target = "id", source = "entityDto.id")
    @Mapping(target = "lastName", source = "entityDto.lastName")
    @Mapping(target = "firstName", source = "entityDto.firstName")
    @Mapping(target = "patronymic", source = "entityDto.patronymic")
    @Mapping(target = "group", source = "entityDto.group")
    Student map(StudentUpdateDto entityDto);

    List<StudentResponseDto> map(List<Student> entities);

    List<StudentShortResponseDto> shortMap(List<Student> entities);
}
