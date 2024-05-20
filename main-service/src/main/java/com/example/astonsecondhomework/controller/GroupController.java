package com.example.astonsecondhomework.controller;

import com.example.astonsecondhomework.aspect.Loggable;
import com.example.astonsecondhomework.controller.api.GroupApi;
import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import com.example.astonsecondhomework.dto.entity.group.GroupResponseDto;
import com.example.astonsecondhomework.dto.entity.group.GroupUpdateDto;
import com.example.astonsecondhomework.service.global.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Loggable
public class GroupController implements GroupApi {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> findAllGroups() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.findAll());
    }

    @SneakyThrows
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponseDto> findGroupById(@PathVariable long groupId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.findById(groupId));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<GroupResponseDto> insertGroup(@RequestBody GroupRequestDto groupRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.insert(groupRequestDto));
    }

    @SneakyThrows
    @PutMapping("/{groupId}")
    public ResponseEntity<GroupResponseDto> updateGroup(@PathVariable long groupId,
                                                        @RequestBody GroupUpdateDto groupUpdateDto) {
        groupUpdateDto.setId(groupId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.update(groupUpdateDto));
    }

    @SneakyThrows
    @PutMapping("/{groupId}/students/{studentIds}")
    public ResponseEntity<GroupResponseDto> insertStudentsToGroup(@PathVariable Long[] studentIds,
                                                                  @PathVariable long groupId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.addStudentToGroup(Arrays.asList(studentIds), groupId));
    }

    @SneakyThrows
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable long groupId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.delete(groupId));
    }

    @SneakyThrows
    @DeleteMapping("students/{studentIds}")
    public ResponseEntity<Boolean> deleteStudentsFromGroup(@PathVariable Long[] studentIds) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupService.deleteStudentFromGroup(Arrays.asList(studentIds)));
    }
}
