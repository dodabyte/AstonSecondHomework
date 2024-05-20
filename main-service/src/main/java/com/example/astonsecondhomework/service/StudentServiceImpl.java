package com.example.astonsecondhomework.service;

import com.example.astonsecondhomework.dto.entity.student.StudentRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.entity.Student;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.RepositoryException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.mapper.StudentMapper;
import com.example.astonsecondhomework.repository.GroupRepository;
import com.example.astonsecondhomework.repository.StudentRepository;
import com.example.astonsecondhomework.service.global.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentResponseDto insert(StudentRequestDto dto) throws RepositoryException, InsertionException {
        Student student = studentRepository.save(studentMapper.map(dto));
        Optional<Student> optional = Optional.ofNullable(student);
        return studentMapper.map(optional.orElseThrow(InsertionException::new));
    }

    @Override
    @Transactional
    public StudentResponseDto update(StudentUpdateDto dto) throws RepositoryException, EntityNotFoundException {
        if (dto == null || dto.getId() == 0 || !containsById(dto.getId())) throw new EntityNotFoundException(new IllegalArgumentException());
        Student student = studentRepository.save(studentMapper.map(dto));
        return studentMapper.map(student);
    }

    @Override
    @Transactional
    public boolean delete(long id) throws RepositoryException, EntityNotFoundException {
        if (!containsById(id)) throw new EntityNotFoundException();
        Optional<Student> optional = studentRepository.findById(id);
        studentRepository.delete(optional.orElseThrow(EntityNotFoundException::new));
        return !containsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean containsById(long id) throws RepositoryException {
        return studentRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> findAll() throws RepositoryException {
        return studentMapper.map(IterableUtils.toList(studentRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto findById(long id) throws RepositoryException, EntityNotFoundException {
        return studentMapper.map(studentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public StudentResponseDto addGroupToStudent(long studentId, long groupId) throws RepositoryException, EntityNotFoundException {
        if (!containsById(studentId) || !groupRepository.existsById(groupId)) throw new EntityNotFoundException();
        Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        student.setGroup(groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new));
        return studentMapper.map(studentRepository.save(student));
    }

    @Override
    @Transactional
    public boolean deleteGroupFromStudent(long studentId) throws RepositoryException, EntityNotFoundException {
        if (!containsById(studentId)) throw new EntityNotFoundException();
        Student student = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        student.setGroup(null);
        studentRepository.save(student);
        return studentRepository.findById(studentId).get().getGroup() == null;
    }
}
