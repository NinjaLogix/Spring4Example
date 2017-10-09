package com.logix.aop;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class HibernateUtilAspect {
    private final Logger log = LoggerFactory.getLogger(HibernateUtilAspect.class);

    @AfterThrowing(pointcut = "execution(* com.logix.utils.HibernateUtil.fetchById(..))",
            throwing = "exception")
    public void afterThrowing_getAllByName(Exception exception){
        log.info("@AfterThrowing ------> " +
                "\n\t Class: " + exception.getClass().toString() +
                "\n\t Cause: " + exception.getCause().toString() +
                "\n\t Message: " + exception.getMessage() +
                "\n\t --> " + new Date());
    }
}
