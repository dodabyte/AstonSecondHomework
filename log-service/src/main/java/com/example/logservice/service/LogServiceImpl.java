package com.example.logservice.service;

import com.example.logservice.service.global.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    @Override
    public String info(String message) {
        log.info(message);
        return message;
    }

    @Override
    public String warning(String message) {
        log.warn(message);
        return message;
    }

    @Override
    public String error(String message) {
        log.error(message);
        return message;
    }

    @Override
    public String debug(String message) {
        log.debug(message);
        return message;
    }

    @Override
    public String trace(String message) {
        log.trace(message);
        return message;
    }
}
