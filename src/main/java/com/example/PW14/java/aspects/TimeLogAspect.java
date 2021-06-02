package com.example.PW14.java.aspects;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class TimeLogAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.example.PW14.java.services.*.*(..))")
    public void allServiceMethods() {}

    @After("allServiceMethods()")
    public void logTime(JoinPoint joinPoint) {
        log.info("New update in "+ (new Date()).toString());
    }
}
