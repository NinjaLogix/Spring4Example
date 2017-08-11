package com.logix.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.ProceedingJoinPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Important note here. When setting your pointcut execution string you need to be exact.
 * com.package.name.class_name(method args)
 *
 *  ex: com.logix.service.CustomerServiceImpl.newCust(..) --> newCust method with one method argument, (Customer cust)
 *      com.logix.service.CustomerServiceImpl.*() ----------> any method with no arguments
 *      com.logix.service.*.*() ----------------------------> any class in the service package with no method arguments
 *
 * Just for lerning purposes I set random annotation pointcuts just to show how they work. @AfterReturning is the most
 * useful one because you can actually do something with an object via proxy after a pointcut executes.
 * References:
 * - <a href="http://www.journaldev.com/2583/spring-aop-example-tutorial-aspect-advice-pointcut-joinpoint-annotations">
 *     JournalDev AOP AspectJ Example</a>
 *
 *  @Author Branden Boyington
 *  @version ${version}
 *  @since 1.0.1-AOP
 */
@Aspect
public class CustomerAspect {

    private final Logger log = LoggerFactory.getLogger(CustomerAspect.class);

    /**
     * Executes this concern before the intended pointcut
     * @Author Branden Boyington
     * @since 1.0.1-AOP
     * @param joinPoint
     */
    @Before("execution(* com.logix.controller.ComponentController.get(..))")
    public void doBefore(JoinPoint joinPoint){
        log.info("@Before ------->" +
                "\n\t Class --> " + joinPoint.getSignature().getClass().toString() +
                "\n\t Name --> " + joinPoint.getSignature().getName() +
                "\n\t --> " + new Date());
    }

    /**
     * Executes this concern after the intended pointcut
     * @Author Branden Boyington
     * @since 1.0.1-AOP
     * @param joinPoint
     */
    @After("execution(* com.logix.service.CustomerServiceImpl.*(..))")
    public void doAfter(JoinPoint joinPoint){
        log.info("@Before ------->" +
                "\n\t --> " + joinPoint.getSignature().getName() +
                "\n\t --> " + new Date());
    }

    /**
     * Executes this concern before and after the intended pointcut
     * @Author Branden Boyington
     * @since 1.0.1-AOP
     * @param precedingJoinPoint
     * @throws Throwable
     */
    @Around("execution(* com.logix.service.CustomerService.newCust(..))")
    public void doAround(ProceedingJoinPoint precedingJoinPoint) throws Throwable{
        log.info("@Around ---> @Before ------->" +
                "\n\t Name --> " + precedingJoinPoint.getSignature().getName() +
                "\n\t Class --> " + precedingJoinPoint.getSignature().getClass().toString() +
                "\n\t Declaring Type Name --> " + precedingJoinPoint.getSignature().getDeclaringTypeName()+
                "\n\t --> " + new Date());
        precedingJoinPoint.proceed();
        log.info("@Around ---> @After ------->" +
                "\n\t Name --> " + precedingJoinPoint.getSignature().getName() +
                "\n\t Class --> " + precedingJoinPoint.getSignature().getClass().toString() +
                "\n\t Declaring Type Name --> " + precedingJoinPoint.getSignature().getDeclaringTypeName() +
                "\n\t --> " + new Date());
    }

    /**
     * Executes this concern after returning a value from the intended pointcut
     * @Author Branden Boyington
     * @since 1.0.1-AOP
     * @param obj
     */
    @AfterReturning(pointcut = "execution(* com.logix.service.CustomerServiceImpl.getCust(..))",
                    returning = "obj")
    public void doAfterReturning(Object obj){
        log.info("@AfterReturning ------> " +
                "\n\t Customer: " + obj.toString() +
                "\n\t --> " + new Date());
    }

    /**
     * Executes this concern after throwing an exception from the intended pointcut.
     * @Author Branden Boyington
     * @since 1.0.1-AOP
     * @param exception
     */
    @AfterThrowing(pointcut = "execution(* com.logix.dao.CustomerDAOImpl.*(..))",
                    throwing = "exception")
    public void doAfterThrowing(Exception exception){
        log.info("@AfterThrowing ------> " +
                "\n\t Class: " + exception.getClass().toString() +
                "\n\t Cause: " + exception.getCause().toString() +
                "\n\t Message: " + exception.getMessage() +
                "\n\t --> " + new Date());
    }
}