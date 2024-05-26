package com.example.astonsecondhomework.controller.api;

import com.example.astonsecondhomework.dto.entity.group.GroupRequestDto;
import com.example.astonsecondhomework.dto.entity.group.GroupResponseDto;
import com.example.astonsecondhomework.dto.entity.group.GroupUpdateDto;
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

@Tag(name = "GroupApi", description = "API for GroupController, which provides the ability to work with group services")
public interface GroupApi {
    @Operation(operationId = "findAllGroups",
            summary = "Getting a list of all groups",
            tags = {"group"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The list of groups has been successfully received",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GroupResponseDto.class),
                                    examples = {@ExampleObject("""
                                        [
                                            {
                                                "id": 1,
                                                "name": "АВТ-043",
                                                "course": 4,
                                                "semester": 8,
                                                "students": [
                                                    {
                                                        "id": 1,
                                                        "firstName": "Иванов",
                                                        "secondName": "Иван",
                                                        "patronymic": "Иванович"
                                                    }
                                                ]
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
    ResponseEntity<List<GroupResponseDto>> findAllGroups();

    @Operation(operationId = "findGroupById",
            summary = "Getting a group by the specified ID",
            tags = {"group"},
            parameters = {@Parameter(name = "groupId",
                    description = "ID of the group to be found",
                    schema = @Schema(implementation = long.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The group was successfully received",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GroupResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1,
                                            "name": "АВТ-043",
                                            "course": 4,
                                            "semester": 8,
                                            "students": [
                                                {
                                                    "id": 1,
                                                    "firstName": "Иванов",
                                                    "secondName": "Иван",
                                                    "patronymic": "Иванович"
                                                }
                                            ]
                                        }
                                        """)
                                    }
                            )}),
                    @ApiResponse(responseCode = "404", description = "Group Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                            )})
            }
    )
    ResponseEntity<GroupResponseDto> findGroupById(@PathVariable long groupId);

    @Operation(operationId = "insertGroup",
            summary = "Creating a group",
            tags = {"group"},
            parameters = {@Parameter(name = "groupRequestDto",
                    description = "Data Transfer Object groups to insert into the database",
                    schema = @Schema(implementation = GroupRequestDto.class))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject("""
                                        {
                                            "name": "АВТ-043",
                                            "course": 4,
                                            "semester": 8
                                        }
                                        """)
                            }
                    )}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "The group has been successfully created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GroupResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1,
                                            "name": "АВТ-043",
                                            "course": 4,
                                            "semester": 8,
                                            "students": null
                                        }
                                        """)
                                    }
                            )}),
                    @ApiResponse(responseCode = "400", description = "Group could not be inserted",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = InsertionException.class)
                            )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                            )})
            }
    )
    ResponseEntity<GroupResponseDto> insertGroup(@RequestBody GroupRequestDto groupRequestDto);

    @Operation(operationId = "updateGroup",
            summary = "Group update",
            tags = {"group"},
            parameters = {@Parameter(name = "groupId",
                        description = "ID of the group to be found",
                        schema = @Schema(implementation = long.class)),
                    @Parameter(name = "groupUpdateDto",
                        description = "Data Transfer Object groups to update in the database",
                        schema = @Schema(implementation = GroupRequestDto.class),
                        examples = {@ExampleObject("""
                                    {
                                        "name": "АВТ-113",
                                        "course": 3,
                                        "semester": 6,
                                        "students": [
                                            {
                                                "id": 1
                                            }
                                        ]
                                    }
                                    """)
                        })},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The group has been successfully updated",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GroupResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1
                                            "name": "АВТ-113",
                                            "course": 3,
                                            "semester": 6,
                                            "students": [
                                                {
                                                    "id": 1,
                                                    "firstName": "Иванов",
                                                    "secondName": "Иван",
                                                    "patronymic": "Иванович"
                                                }
                                            ]
                                        }
                                        """)
                                    }
                            )}),
                    @ApiResponse(responseCode = "404", description = "Group Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                            )})
            }
    )
    ResponseEntity<GroupResponseDto> updateGroup(@PathVariable long groupId,
                                                 @RequestBody GroupUpdateDto groupUpdateDto);

    @Operation(operationId = "insertStudentsToGroup",
            summary = "Adding a students to a group by the specified IDs",
            tags = {"group"},
            parameters = {@Parameter(name = "studentIds",
                        description = "An array IDs of the students to be found",
                        schema = @Schema(implementation = long[].class)),
                    @Parameter(name = "groupId",
                        description = "ID of the group to be found",
                        schema = @Schema(implementation = long.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Students have been successfully added to the group",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GroupResponseDto.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "id": 1
                                            "name": "АВТ-113",
                                            "course": 3,
                                            "semester": 6,
                                            "students": [
                                                {
                                                    "id": 1,
                                                    "firstName": "Иванов",
                                                    "secondName": "Иван",
                                                    "patronymic": "Иванович"
                                                }
                                            ]
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
    ResponseEntity<GroupResponseDto> insertStudentsToGroup(@PathVariable Long[] studentIds,
                                                           @PathVariable long groupId);

    @Operation(operationId = "deleteGroup",
            summary = "Deleting a group",
            tags = {"student"},
            parameters = {@Parameter(name = "groupId",
                    description = "ID of the group to be found",
                    schema = @Schema(implementation = long.class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "The group has been successfully deleted",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Boolean.class),
                                    examples = {@ExampleObject("""
                                        true
                                        """)
                                    }
                            )}),
                    @ApiResponse(responseCode = "404", description = "Group Not Found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EntityNotFoundException.class)
                            )}),
                    @ApiResponse(responseCode = "500", description = "Server Error",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RepositoryException.class)
                            )})
            }
    )
    ResponseEntity<Boolean> deleteGroup(@PathVariable long groupId);

    @Operation(operationId = "deleteStudentsFromGroup",
            summary = "Deleting a group from a group by the specified ID",
            tags = {"student"},
            parameters = {@Parameter(name = "studentIds",
                    description = "An array IDs of the students to be found",
                    schema = @Schema(implementation = long[].class))},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Students have been successfully deleted from the group",
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
    ResponseEntity<Boolean> deleteStudentsFromGroup(@PathVariable Long[] studentIds);
}
