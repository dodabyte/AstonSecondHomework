package com.example.logservice.service.global;

public interface LogService {
    String info(String message);
    String warning(String message);
    String error(String message);
    String debug(String message);
    String trace(String message);
}
