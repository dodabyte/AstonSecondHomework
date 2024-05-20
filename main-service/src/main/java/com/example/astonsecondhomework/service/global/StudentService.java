package com.example.astonsecondhomework.service.global;

import com.example.astonsecondhomework.dto.entity.student.StudentRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.exception.RepositoryException;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;

import java.util.List;

public interface StudentService {
    StudentResponseDto insert(StudentRequestDto dto) throws RepositoryException, InsertionException;
    StudentResponseDto update(StudentUpdateDto dto) throws RepositoryException, EntityNotFoundException;
    boolean delete(long id) throws RepositoryException, EntityNotFoundException;
    boolean containsById(long id) throws RepositoryException;
    List<StudentResponseDto> findAll() throws RepositoryException;
    StudentResponseDto findById(long id) throws RepositoryException, EntityNotFoundException;
    StudentResponseDto addGroupToStudent(long studentId, long groupId) throws RepositoryException, EntityNotFoundException;
    boolean deleteGroupFromStudent(long studentId) throws RepositoryException, EntityNotFoundException;
}
