package com.logix.aop;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ControllerAspect {
    private final Logger log = LoggerFactory.getLogger(ControllerAspect.class);

    @Before("execution(* com.logix.controller.ServiceController.createNewCust(..))")
    public void before_createNewCust(JoinPoint joinpoint){
        log.info("@Before ------->" +
                "\n\t --> " + joinpoint.getSignature().getName() +
                "\n\t --> " + new Date());
    }
}
