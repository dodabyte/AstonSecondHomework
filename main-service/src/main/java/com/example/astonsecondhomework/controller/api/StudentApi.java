package com.example.astonsecondhomework.controller.api;

import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentRequestDto;
import com.example.astonsecondhomework.dto.entity.student.StudentResponseDto;
import com.example.astonsecondhomework.dto.entity.student.StudentUpdateDto;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.exception.RepositoryException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "StudentApi", description = "API for StudentController, which provides the ability to work with student services")
public interface StudentApi {
    @Operation(operationId = "getAllStudents",
            summary = "Getting a list of all students",
            tags = {"student"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The list of students has been successfully received",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDto.class),
                                    examples = {@ExampleObject("""
                                        [
                                            {
                                                "id": 1,
                                                "firstName": "Иванов",
                                                "secondName": "Иван",
                                                "patronymic": "Иванович"
                                                "group":
                                                    {
                                                        "id": 1,
                                                        "name": "АВТ-043",
                                                        "course": 4,
                                                        "semester": 8
                                                    }
                                            }
                                        ]
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<List<StudentResponseDto>> findAllStudents();

    @Operation(operationId = "getStudentById",
            summary = "Getting a student by the specified ID",
            tags = {"student"},
            parameters = {@Parameter(name = "studentId",
                    description = "ID of the student to be found",
                    schema = @Schema(implementation = long.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The student was successfully received",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1,
                                            "firstName": "Иванов",
                                            "secondName": "Иван",
                                            "patronymic": "Иванович"
                                            "group":
                                                {
                                                    "id": 1,
                                                    "name": "АВТ-043",
                                                    "course": 4,
                                                    "semester": 8
                                                }
                                        }
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "404", description = "Student Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<StudentResponseDto> findStudentById(@PathVariable long studentId);

    @Operation(operationId = "insertStudent",
            summary = "Creating a student",
            tags = {"student"},
            parameters = {@Parameter(name = "studentRequestDto",
                    description = "Data Transfer Object student to insert into the database",
                    schema = @Schema(implementation = StudentRequestDto.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The student has been successfully created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1,
                                            "firstName": "Иванов",
                                            "secondName": "Иван",
                                            "patronymic": "Иванович"
                                            "group":
                                                {
                                                    "id": 1
                                                }
                                        }
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "400", description = "Student could not be inserted",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = InsertionException.class)
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<StudentResponseDto> insertStudent(@RequestBody StudentRequestDto studentRequestDto);

    @Operation(operationId = "updateStudent",
            summary = "Student update",
            tags = {"student"},
            parameters = {@Parameter(name = "studentId",
                    description = "ID of the group to be found",
                    schema = @Schema(implementation = long.class)),
                    @Parameter(name = "studentUpdateDto",
                            description = "Data Transfer Object student to update in the database",
                            schema = @Schema(implementation = GroupRequestDto.class),
                            examples = {@ExampleObject("""
                                 {
                                        "id": 1,
                                        "firstName": "Васильев",
                                        "secondName": "Василий",
                                        "patronymic": "Васильевич"
                                        "group":
                                            {
                                                "id": 2
                                            }
                                    }
                                    """)
                            })},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The student has been successfully updated",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1,
                                            "firstName": "Иванов",
                                            "secondName": "Иван",
                                            "patronymic": "Иванович"
                                            "group":
                                                {
                                                    "id": 1,
                                                    "name": "АВТ-043",
                                                    "course": 4,
                                                    "semester": 8
                                                }
                                        }
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "404", description = "Student Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<StudentResponseDto> updateStudent(@PathVariable long studentId,
                                                     @RequestBody StudentUpdateDto studentUpdateDto);

    @Operation(operationId = "insertGroupInStudent",
            summary = "Adding a student to a group by the specified IDs",
            tags = {"student"},
            parameters = {@Parameter(name = "studentId",
                    description = "ID of the student to be found",
                    schema = @Schema(implementation = long[].class)),
                    @Parameter(name = "groupId",
                            description = "ID of the group to be found",
                            schema = @Schema(implementation = long.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The student has been successfully added to the group",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StudentResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1,
                                            "firstName": "Иванов",
                                            "secondName": "Иван",
                                            "patronymic": "Иванович"
                                            "group":
                                                {
                                                    "id": 1,
                                                    "name": "АВТ-043",
                                                    "course": 4,
                                                    "semester": 8
                                                }
                                        }
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "404", description = "Student or Group Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<StudentResponseDto> insertGroupInStudent(@PathVariable long studentId,
                                                            @PathVariable long groupId);

    @Operation(operationId = "deleteStudent",
            summary = "Deleting a student",
            tags = {"student"},
            parameters = {@Parameter(name = "studentId",
                    description = "ID of the student to be found",
                    schema = @Schema(implementation = long.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The student has been successfully deleted",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class),
                                    examples = {@ExampleObject("""
                                        true
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "404", description = "Student Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<Boolean> deleteStudent(@PathVariable long studentId);

    @Operation(operationId = "deleteGroupFromStudent",
            summary = "Deleting a student from a group by the specified ID",
            tags = {"student"},
            parameters = {@Parameter(name = "studentId",
                    description = "ID of the student to be found",
                    schema = @Schema(implementation = long[].class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The student has been successfully deleted from the group",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class),
                                    examples = {@ExampleObject("""
                                        true
                                        """)
                                    }
                    )}),
                    @ApiResponse(responseCode = "404", description = "Student Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                    )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                    )})
            }
    )
    ResponseEntity<Boolean> deleteGroupFromStudent(@PathVariable long studentId);
}
