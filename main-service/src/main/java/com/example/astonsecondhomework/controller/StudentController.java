package com.example.astonsecondhomework.controller;


import com.example.astonsecondhomework.aspect.Loggable;
import com.example.astonsecondhomework.controller.api.StudentApi;
import com.example.astonsecondhomework.dto.entity.student.StudentRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.service.global.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Loggable
public class StudentController implements StudentApi {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAllStudents() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.findAll());
    }

    @SneakyThrows
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> findStudentById(@PathVariable long studentId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.findById(studentId));
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<StudentResponseDto> insertStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.insert(studentRequestDto));
    }

    @SneakyThrows
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable long studentId,
                                                            @RequestBody StudentUpdateDto studentUpdateDto) {
        studentUpdateDto.setId(studentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.update(studentUpdateDto));
    }

    @SneakyThrows
    @PutMapping("/{studentId}/group/{groupId}")
    public ResponseEntity<StudentResponseDto> insertGroupInStudent(@PathVariable long studentId,
                                                                   @PathVariable long groupId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.addGroupToStudent(studentId, groupId));
    }

    @SneakyThrows
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable long studentId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.delete(studentId));
    }

    @SneakyThrows
    @DeleteMapping("/{studentId}/group")
    public ResponseEntity<Boolean> deleteGroupFromStudent(@PathVariable long studentId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.deleteGroupFromStudent(studentId));
    }
}
