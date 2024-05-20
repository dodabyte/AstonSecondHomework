package com.example.logservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "LogApi", description = "API for LogController, which provides the ability to work with logging services")
public interface LogApi {
    @Operation(operationId = "info",
            summary = "Logging of an info type message",
            tags = {"log"},
            parameters = {@Parameter(name = "message",
                    description = "The message that will be sent to the logging service",
                    schema = @Schema(implementation = String.class))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                    @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                            }
                    )}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "An info message has been successfully logged",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                        @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                                    }
                            )})
            }
    )
    ResponseEntity<String> info(@RequestBody String message);

    @Operation(operationId = "warning",
            summary = "Logging of an warning type message",
            tags = {"log"},
            parameters = {@Parameter(name = "message",
                    description = "The message that will be sent to the logging service",
                    schema = @Schema(implementation = String.class))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                    @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                            }
                    )}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "An warning message has been successfully logged",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                        @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                                    }
                            )})
            }
    )
    ResponseEntity<String> warning(@RequestBody String message);

    @Operation(operationId = "error",
            summary = "Logging of an error type message",
            tags = {"log"},
            parameters = {@Parameter(name = "message",
                    description = "The message that will be sent to the logging service",
                    schema = @Schema(implementation = String.class))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                    @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                            }
                    )}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "An error message has been successfully logged",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                        @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                                    }
                            )})
            }
    )
    ResponseEntity<String> error(@RequestBody String message);

    @Operation(operationId = "debug",
            summary = "Logging of an debug type message",
            tags = {"log"},
            parameters = {@Parameter(name = "message",
                    description = "The message that will be sent to the logging service",
                    schema = @Schema(implementation = String.class))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                    @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                            }
                    )}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "An debug message has been successfully logged",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                        @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                                    }
                            )})
            }
    )
    ResponseEntity<String> debug(@RequestBody String message);

    @Operation(operationId = "trace",
            summary = "Logging of an trace type message",
            tags = {"log"},
            parameters = {@Parameter(name = "message",
                    description = "The message that will be sent to the logging service",
                    schema = @Schema(implementation = String.class))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                        @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                            }
                    )}),
            responses = {
                    @ApiResponse(responseCode = "200", description = "An trace message has been successfully logged",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = {@ExampleObject("""
                                        {
                                            "message": "Before calling method {methodName} of class {className}."
                                        }
                                        """),
                                        @ExampleObject("""
                                        {
                                            "message": "After calling method {methodName} of class {className}. Result: {methodResult}."
                                        }
                                        """)
                                    }
                            )})
            }
    )
    ResponseEntity<String> trace(@RequestBody String message);
}
