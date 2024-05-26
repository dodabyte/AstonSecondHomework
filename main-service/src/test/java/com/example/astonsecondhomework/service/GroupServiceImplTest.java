package com.example.astonsecondhomework.service;

import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import com.example.astonsecondhomework.dto.entity.group.GroupResponseDto;
import com.example.astonsecondhomework.dto.entity.group.GroupUpdateDto;
import com.example.astonsecondhomework.entity.Group;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.repository.GroupRepository;
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
class GroupServiceImplTest {
    @Autowired
    private GroupServiceImpl groupService;
    @MockBean
    private GroupRepository groupRepository;

    @Test
    void insert() throws InsertionException {
        long expectedId = 1;
        String expectedName = "Test Name";
        int expectedCourse = 4;
        int expectedSemester = 8;

        Group group = new Group(expectedId, expectedName, expectedCourse, expectedSemester, List.of());
        GroupRequestDto dto = new GroupRequestDto(expectedName, expectedCourse, expectedSemester);

        Mockito.doReturn(group).when(groupRepository).save(Mockito.any(Group.class));
        groupService.insert(dto);

        ArgumentCaptor<Group> argumentCaptor = ArgumentCaptor.forClass(Group.class);
        Mockito.verify(groupRepository).save(argumentCaptor.capture());

        Group result = argumentCaptor.getValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedName, result.getName()),
                () -> Assertions.assertEquals(expectedCourse, result.getCourse()),
                () -> Assertions.assertEquals(expectedSemester, result.getSemester())
        );
    }

    @Test
    void insertInsertionException() {
        String expectedName = "Test Name";
        int expectedCourse = 4;
        int expectedSemester = 8;

        GroupRequestDto dto = new GroupRequestDto(expectedName, expectedCourse, expectedSemester);

        Mockito.doReturn(null).when(groupRepository).save(Mockito.any(Group.class));

        InsertionException exception = Assertions.assertThrows(
                InsertionException.class,
                () -> groupService.insert(dto));
        Assertions.assertEquals(InsertionException.class, exception.getClass());
    }

    @Test
    void update() throws EntityNotFoundException {
        long expectedId = 1;
        String expectedName = "New Test Name";
        int expectedCourse = 3;
        int expectedSemester = 5;

        GroupUpdateDto dto = new GroupUpdateDto(expectedId, expectedName, expectedCourse, expectedSemester);

        Mockito.doReturn(true).when(groupRepository).existsById(Mockito.anyLong());
        groupService.update(dto);

        ArgumentCaptor<Group> argumentCaptor = ArgumentCaptor.forClass(Group.class);
        Mockito.verify(groupRepository).save(argumentCaptor.capture());

        Group result = argumentCaptor.getValue();
        Assertions.assertEquals(expectedId, result.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedId, result.getId()),
                () -> Assertions.assertEquals(expectedName, result.getName()),
                () -> Assertions.assertEquals(expectedCourse, result.getCourse()),
                () -> Assertions.assertEquals(expectedSemester, result.getSemester())
        );
    }

    @Test
    void updateEntityNotFoundException() {
        long expectedId = 1;
        String expectedName = "New Test Name";
        int expectedCourse = 3;
        int expectedSemester = 5;

        GroupUpdateDto dto = new GroupUpdateDto(expectedId, expectedName, expectedCourse, expectedSemester);

        Mockito.doReturn(false).when(groupRepository).existsById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> groupService.update(dto));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void delete() throws EntityNotFoundException {
        long expectedId = 1;

        Mockito.doReturn(Optional.of(new Group())).when(groupRepository).findById(Mockito.anyLong());
        Mockito.doReturn(true).when(groupRepository).existsById(Mockito.anyLong());

        boolean result = groupService.delete(expectedId);

        Assertions.assertFalse(result);

        ArgumentCaptor<Group> argumentCaptor = ArgumentCaptor.forClass(Group.class);
        Mockito.verify(groupRepository).delete(argumentCaptor.capture());

        Group resultGroup = argumentCaptor.getValue();
        Assertions.assertEquals(0, resultGroup.getId());
    }

    @Test
    void deleteEntityNotFoundException() {
        long expectedId = 1;

        Mockito.doReturn(Optional.empty()).when(groupRepository).findById(Mockito.anyLong());
        Mockito.doReturn(false).when(groupRepository).existsById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> groupService.delete(expectedId));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }

    @Test
    void containsById() {
        long expectedId = 1;

        Mockito.doReturn(true).when(groupRepository).existsById(Mockito.anyLong());

        boolean result = groupService.containsById(expectedId);

        Assertions.assertTrue(result);
    }

    @Test
    void findAll() {
        groupService.findAll();
        Mockito.verify(groupRepository).findAll();
    }

    @Test
    void findById() throws EntityNotFoundException {
        long expectedId = 4;
        String expectedName = "юбр-045";
        int expectedCourse = 4;
        int expectedSemester = 8;

        Optional<Group> group = Optional.of(new Group(expectedId, expectedName, expectedCourse, expectedSemester, null));
        GroupResponseDto expectedGroupResponseDto = new GroupResponseDto(expectedId, expectedName, expectedCourse, expectedSemester, null);

        Mockito.doReturn(true).when(groupRepository).existsById(Mockito.anyLong());
        Mockito.doReturn(group).when(groupRepository).findById(Mockito.anyLong());

        GroupResponseDto actualGroupResponseDto = groupService.findById(expectedId);

        Assertions.assertEquals(expectedGroupResponseDto, actualGroupResponseDto);
    }

    @Test
    void findByIdEntityNotFoundException() {
        Mockito.doReturn(false).when(groupRepository).existsById(Mockito.anyLong());

        EntityNotFoundException exception = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> groupService.findById(Mockito.anyLong()));
        Assertions.assertEquals(EntityNotFoundException.class, exception.getClass());
    }
}