package com.example.astonsecondhomework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private KafkaTemplate<String, String> template;

    @Around("@annotation(Loggable) || @within(Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Loggable loggable = joinPoint.getTarget().getClass().getAnnotation(Loggable.class);
        if (loggable == null) {
            loggable = method.getAnnotation(Loggable.class);
        }
        LogType logType = loggable.type();

        String methodName = method.getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        beforeLogAround(logType, methodName, className);

        Object result = joinPoint.proceed();

        afterLogAround(logType, methodName, className, result);

        return result;
    }

    private void beforeLogAround(LogType logType, String methodName, String className) {
        String message = "Before calling method " + methodName + " of class " + className + ".";
        sendLogMessage(logType, message);
    }

    private void afterLogAround(LogType logType, String methodName, String className, Object result) {
        String message = "After calling method " + methodName + " of class " + className + ". Result: " + result + ".";
        sendLogMessage(logType, message);
    }

    private void sendLogMessage(LogType logType, String message) {
        template.send(logType.getType(), message);
    }
}
