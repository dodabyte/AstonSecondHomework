package com.example.astonsecondhomework.aspect;

public enum LogType {
    INFO("log-info"),
    WARNING("log-warn"),
    ERROR("log-error"),
    DEBUG("log-debug"),
    TRACE("log-trace");

    private final String type;

    LogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
