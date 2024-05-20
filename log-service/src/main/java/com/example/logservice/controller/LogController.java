package com.example.logservice.controller;

import com.example.logservice.controller.api.LogApi;
import com.example.logservice.service.global.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs/")
@RequiredArgsConstructor
public class LogController implements LogApi {
    private final LogService logService;

    @PostMapping("/info")
    @KafkaListener(topics = "log-info")
    public ResponseEntity<String> info(@RequestBody String message) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.info(message));
    }

    @PostMapping("/warning")
    @KafkaListener(topics = "log-warning")
    public ResponseEntity<String> warning(@RequestBody String message) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.warning(message));
    }

    @PostMapping("/error")
    @KafkaListener(topics = "log-error")
    public ResponseEntity<String> error(@RequestBody String message) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.error(message));
    }

    @PostMapping("/debug")
    @KafkaListener(topics = "log-debug")
    public ResponseEntity<String> debug(@RequestBody String message) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.debug(message));
    }

    @PostMapping("/trace")
    @KafkaListener(topics = "log-trace")
    public ResponseEntity<String> trace(@RequestBody String message) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logService.trace(message));
    }
}
