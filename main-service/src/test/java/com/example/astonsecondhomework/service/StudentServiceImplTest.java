package com.example.astonsecondhomework.service;

import com.example.astonsecondhomework.dto.entity.group.GroupShortRequestDto;
import com.example.astonsecondhomework.dto.entity.group.GroupShortUpdateDto;
import com.example.astonsecondhomework.dto.entity.student.StudentRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.entity.Group;
import com.example.astonsecondhomework.entity.Student;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.repository.GroupRepository;
import com.example.astonsecondhomework.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class StudentServiceImplTest {
    @Autowired
    private StudentServiceImpl studentService;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private GroupRepository groupRepository;

    @Test
    void insert() throws InsertionException {
        long expectedId = 1;
        String expectedLastName = "Test Last Name";
        String expectedFirstName = "Test First Name";
        String expectedPatronymic = "Test Patronymic";
        long expectedGroupId = 4;
        String expectedGroupName = "Test Group Name";
        int expectedGroupCourse = 4;
        int expectedGroupSemester = 8;

        Group group = new Group(expectedId, expectedGroupName, expectedGroupCourse, expectedGroupSemester, null);
        GroupShortRequestDto expectedGroup = new GroupShortRequestDto(expectedGroupId);

        Student student = new Student(expectedId, expectedLastName, expectedFirstName, expectedPatronymic, group);
        group.setStudents(List.of(student));
        StudentRequestDto dto = new StudentRequestDto(expectedLastName, expectedFirstName, expectedPatronymic, expectedGroup);

        Mockito.doReturn(student).when(studentRepository).save(Mockito.any(Student.class));
        studentService.insert(dto);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).save(argumentCaptor.capture());

        Student result = argumentCaptor.getValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedLastName, result.getLastName()),
                () -> Assertions.assertEquals(expectedFirstName, result.getFirstName()),
                () -> Assertions.assertEquals(expectedPatronymic, result.getPatronymic()),
                () -> Assertions.assertEquals(expectedGroupId, result.getGroup().getId())
        );
    }

    @Test
    void insertInsertionException() {
        String expectedLastName = "Test Last Name";
        String expectedFirstName = "Test First Name";
        String expectedPatronymic = "Test Patronymic";
        long expectedGroupId = 4;

        GroupShortRequestDto expectedGroup = new GroupShortRequestDto(expectedGroupId);

        StudentRequestDto dto = new StudentRequestDto(expectedLastName, expectedFirstName, expectedPatronymic, expectedGroup);

        Mockito.doReturn(null).when(studentRepository).save(Mockito.any(Student.class));

        InsertionException exception = Assertions.assertThrows(
                InsertionException.class,
                () -> studentService.insert(dto));
        Assertions.assertEquals(InsertionException.class, exception.getClass());
    }

    @Test
    void update() throws EntityNotFoundException {
        long expectedId = 1;
        String expectedLastName = "New Test Last Name";
        String expectedFirstName = "New Test First Name";
        String expectedPatronymic = "New Test Patronymic";
        long expectedGroupId = 4;

        GroupShortUpdateDto expectedGroup = new GroupShortUpdateDto(expectedGroupId);

        StudentUpdateDto dto = new StudentUpdateDto(expectedId, expectedLastName, expectedFirstName, expectedPatronymic, expectedGroup);

        Mockito.doReturn(true).when(studentRepository).existsById(Mockito.anyLong());
        studentService.update(dto);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).save(argumentCaptor.capture());

        Student result = argumentCaptor.getValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedId, result.getId()),
                () -> Assertions.assertEquals(expectedLastName, result.getLastName()),
                () -> Assertions.assertEquals(expectedFirstName, result.getFirstName()),
                () -> Assertions.assertEquals(expectedPatronymic, result.getPatronymic()),
                () -> Assertions.assertEquals(expectedGroupId, result.getGroup().getId())
        );
    }

    @Test
    void updateEntityNotFoundException() {
        String expectedLastName = "New Test Last Name";
        String expectedFirstName = "New Test First Name";
        String expectedPatronymic = "New Test Patronymic";
        long expectedGroupId = 4;

        GroupShortUpdateDto expectedGroup = new GroupShortUpdateDto(expectedGroupId);

        StudentUpdateDto dto = new StudentUpdateDto(expectedGroupId, expectedLastName, expectedFirstName, expectedPatronymic, expectedGroup);

        Mockito.doReturn(false).when(studentRepository).existsById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> studentService.update(dto));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void delete() throws EntityNotFoundException {
        long expectedId = 1;

        Mockito.doReturn(Optional.of(new Student())).when(studentRepository).findById(Mockito.anyLong());
        Mockito.doReturn(true).when(studentRepository).existsById(Mockito.anyLong());

        boolean result = studentService.delete(expectedId);

        Assertions.assertFalse(result);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).delete(argumentCaptor.capture());

        Student resultStudent = argumentCaptor.getValue();
        Assertions.assertEquals(0, resultStudent.getId());
    }

    @Test
    void deleteEntityNotFoundException() {
        long expectedId = 1;

        Mockito.doReturn(Optional.empty()).when(studentRepository).findById(Mockito.anyLong());
        Mockito.doReturn(false).when(studentRepository).existsById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> studentService.delete(expectedId));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void containsById() {
        long expectedId = 1;

        Mockito.doReturn(true).when(studentRepository).existsById(Mockito.anyLong());

        boolean result = studentService.containsById(expectedId);

        Assertions.assertTrue(result);
    }

    @Test
    void findAll() {
        studentService.findAll();
        Mockito.verify(studentRepository).findAll();
    }

    @Test
    void findById() throws EntityNotFoundException {
        long expectedId = 1;
        String expectedLastName = "Test Last Name";
        String expectedFirstName = "Test First Name";
        String expectedPatronymic = "Test Patronymic";
        long expectedGroupId = 4;
        String expectedGroupName = "Test Name";
        int expectedGroupCourse = 4;
        int expectedGroupSemester = 8;

        Group group = new Group(expectedGroupId, expectedGroupName, expectedGroupCourse, expectedGroupSemester, null);

        Optional<Student> student =
                Optional.of(new Student(expectedId, expectedLastName, expectedFirstName, expectedPatronymic, group));

        group.setStudents(List.of(student.get()));

        Mockito.doReturn(true).when(studentRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(student).when(studentRepository).findById(Mockito.anyLong());

        StudentResponseDto dto = studentService.findById(expectedId);

        Assertions.assertEquals(expectedId, dto.getId());
    }

    @Test
    void findByIdEntityNotFoundException() {
        Mockito.doReturn(false).when(studentRepository).existsById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> studentService.findById(Mockito.anyLong()));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void addGroupToStudent() throws EntityNotFoundException {
        long expectedStudentId = 1;
        String expectedLastName = "Test Last Name";
        String expectedFirstName = "Test First Name";
        String expectedPatronymic = "Test Patronymic";
        long expectedGroupId = 4;
        String expectedGroupName = "Test Name";
        int expectedGroupCourse = 4;
        int expectedGroupSemester = 8;

        Optional<Group> group =
                Optional.of(new Group(expectedGroupId, expectedGroupName, expectedGroupCourse, expectedGroupSemester, null));

        Optional<Student> student =
                Optional.of(new Student(expectedStudentId, expectedLastName, expectedFirstName, expectedPatronymic, group.get()));

        group.get().setStudents(List.of(student.get()));

        Mockito.doReturn(true).when(studentRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(true).when(groupRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(student).when(studentRepository).findById(Mockito.anyLong());
        Mockito.doReturn(group).when(groupRepository).findById(Mockito.anyLong());
        studentService.addGroupToStudent(expectedStudentId, expectedGroupId);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).save(argumentCaptor.capture());

        Student result = argumentCaptor.getValue();
        Assertions.assertEquals(group.get(), result.getGroup());
    }

    @Test
    void addGroupToStudentEntityNotFoundException() {
        long expectedStudentId = 1;
        long expectedGroupId = 4;

        Mockito.doReturn(false).when(studentRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(false).when(groupRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(Optional.empty()).when(studentRepository).findById(Mockito.anyLong());
        Mockito.doReturn(Optional.empty()).when(groupRepository).findById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> studentService.addGroupToStudent(expectedStudentId, expectedGroupId));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void deleteGroupFromStudent() throws EntityNotFoundException {
        long expectedStudentId = 1;
        String expectedLastName = "Test Last Name";
        String expectedFirstName = "Test First Name";
        String expectedPatronymic = "Test Patronymic";
        long expectedGroupId = 4;
        String expectedGroupName = "Test Name";
        int expectedGroupCourse = 4;
        int expectedGroupSemester = 8;

        Optional<Group> group =
                Optional.of(new Group(expectedGroupId, expectedGroupName, expectedGroupCourse, expectedGroupSemester, null));

        Optional<Student> student =
                Optional.of(new Student(expectedStudentId, expectedLastName, expectedFirstName, expectedPatronymic, group.get()));

        group.get().setStudents(List.of(student.get()));

        Mockito.doReturn(true).when(studentRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(true).when(groupRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(student).when(studentRepository).findById(Mockito.anyLong());
        Mockito.doReturn(group).when(groupRepository).findById(Mockito.anyLong());
        studentService.deleteGroupFromStudent(expectedStudentId);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepository).save(argumentCaptor.capture());

        Student result = argumentCaptor.getValue();
        Assertions.assertNull(result.getGroup());
    }

    @Test
    void deleteGroupFromStudentEntityNotFoundException() {
        long expectedId = 1;

        Mockito.doReturn(Optional.empty()).when(studentRepository).findById(Mockito.anyLong());
        Mockito.doReturn(false).when(studentRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(0).when(studentRepository).save(Mockito.any(Student.class));

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> studentService.deleteGroupFromStudent(expectedId));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }
}