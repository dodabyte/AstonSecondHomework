package com.example.astonsecondhomework.service.global;

import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import com.example.astonsecondhomework.dto.entity.group.GroupResponseDto;
import com.example.astonsecondhomework.dto.entity.group.GroupUpdateDto;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.exception.RepositoryException;

import java.util.List;

public interface GroupService {
    GroupResponseDto insert(GroupRequestDto dto) throws RepositoryException, InsertionException;
    GroupResponseDto update(GroupUpdateDto dto) throws RepositoryException, EntityNotFoundException;
    boolean delete(long id) throws RepositoryException, EntityNotFoundException;
    boolean containsById(long id) throws RepositoryException;
    List<GroupResponseDto> findAll() throws RepositoryException;
    GroupResponseDto findById(long id) throws RepositoryException, EntityNotFoundException;
    GroupResponseDto addStudentToGroup(List<Long> studentIds, long groupId) throws RepositoryException, EntityNotFoundException;
    boolean deleteStudentFromGroup(List<Long> studentIds) throws RepositoryException, EntityNotFoundException;
}
