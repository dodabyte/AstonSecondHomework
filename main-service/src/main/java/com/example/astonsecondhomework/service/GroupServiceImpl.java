package com.example.astonsecondhomework.service;

import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import com.example.astonsecondhomework.dto.entity.group.GroupResponseDto;
import com.example.astonsecondhomework.dto.entity.group.GroupUpdateDto;
import com.example.astonsecondhomework.entity.Group;
import com.example.astonsecondhomework.entity.Student;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.exception.RepositoryException;
import com.example.astonsecondhomework.mapper.GroupMapper;
import com.example.astonsecondhomework.repository.GroupRepository;
import com.example.astonsecondhomework.repository.StudentRepository;
import com.example.astonsecondhomework.service.global.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GroupMapper groupMapper;

    @Override
    @Transactional
    public GroupResponseDto insert(GroupRequestDto dto) throws RepositoryException, InsertionException {
        Group group = groupRepository.save(groupMapper.map(dto));
        Optional<Group> optional = Optional.ofNullable(group);
        return groupMapper.map(optional.orElseThrow(InsertionException::new));
    }

    @Override
    @Transactional
    public GroupResponseDto update(GroupUpdateDto dto) throws RepositoryException, EntityNotFoundException {
        if (dto == null || dto.getId() == 0 || !containsById(dto.getId())) throw new EntityNotFoundException(new IllegalArgumentException());
        Group group = groupRepository.save(groupMapper.map(dto));
        return groupMapper.map(group);
    }

    @Override
    @Transactional
    public boolean delete(long id) throws RepositoryException, EntityNotFoundException {
        if (!containsById(id)) throw new EntityNotFoundException();
        Optional<Group> optional = groupRepository.findById(id);
        groupRepository.delete(optional.orElseThrow(EntityNotFoundException::new));
        return !containsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean containsById(long id) throws RepositoryException {
        return groupRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupResponseDto> findAll() throws RepositoryException {
        return groupMapper.map(IterableUtils.toList(groupRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public GroupResponseDto findById(long id) throws RepositoryException, EntityNotFoundException {
        return groupMapper.map(groupRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public GroupResponseDto addStudentToGroup(List<Long> studentIds, long groupId) throws RepositoryException, EntityNotFoundException {
        if (!containsById(groupId)) throw new EntityNotFoundException();
        Group updatedGroup = groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new);
        for (long studentId : studentIds) {
            if (!studentRepository.existsById(studentId)) throw new EntityNotFoundException();
            Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
            student.setGroup(updatedGroup);
            studentRepository.save(student);
        }
        return groupMapper.map(updatedGroup);
    }

    @Override
    @Transactional
    public boolean deleteStudentFromGroup(List<Long> studentIds) throws RepositoryException, EntityNotFoundException {
        List<Boolean> isUpdatedStudents = new ArrayList<>();
        for (long studentId : studentIds) {
            if (!studentRepository.existsById(studentId)) throw new EntityNotFoundException();
            Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
            student.setGroup(null);
            studentRepository.save(student);
            isUpdatedStudents.add(studentRepository.findById(studentId).get().getGroup() == null);
        }
        return isUpdatedStudents.stream().allMatch(updated -> updated);
    }
}
