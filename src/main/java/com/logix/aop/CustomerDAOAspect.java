package com.logix.aop;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class CustomerDAOAspect {
    private final Logger log = LoggerFactory.getLogger(CustomerDAOAspect.class);

    @After("execution(* com.logix.data.CustomerDAOImpl.createCustomer(..))")
    public void after_create(JoinPoint joinPoint){
        log.info("@After ------->" +
                "\n\t --> " + joinPoint.getSignature().getName() +
                "\n\t --> " + new Date());
    }
}
