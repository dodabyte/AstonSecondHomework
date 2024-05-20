package com.example.astonsecondhomework.handler;

import com.example.astonsecondhomework.dto.exception.ErrorDto;
import com.example.astonsecondhomework.exception.EntityNotFoundException;
import com.example.astonsecondhomework.exception.InsertionException;
import com.example.astonsecondhomework.exception.JsonException;
import com.example.astonsecondhomework.exception.RepositoryException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exception(Exception exception) {
        kafkaTemplate.send("log-error", "Error " + "(" + exception.getClass().getSimpleName() + "): " +
                exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> notFound(EntityNotFoundException exception) {
        kafkaTemplate.send("log-error", "Error " + "(" + exception.getClass().getSimpleName() + "): " +
                exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(InsertionException.class)
    public ResponseEntity<ErrorDto> badRequest(InsertionException exception) {
        kafkaTemplate.send("log-error", "Error " + "(" + exception.getClass().getSimpleName() + "): " +
                exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorDto> internalServer(RepositoryException exception) {
        kafkaTemplate.send("log-error", "Error " + "(" + exception.getClass().getSimpleName() + "): " +
                exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(JsonException.class)
    public ResponseEntity<ErrorDto> notAcceptable(JsonException exception) {
        kafkaTemplate.send("log-error", "Error " + "(" + exception.getClass().getSimpleName() + "): " +
                exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorDto(exception.getMessage()));
    }
}
