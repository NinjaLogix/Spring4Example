package com.logix.aop;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.AfterReturning;
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
 * Just for learning purposes I set random annotation pointcuts just to show how they work. @AfterReturning is the most
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
public class CustomerServiceAspect {
    private final Logger log = LoggerFactory.getLogger(CustomerServiceAspect.class);

    @Around("execution(* com.logix.service.CustomerService.createCustomer(..))")
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

    @AfterReturning(pointcut = "execution(* com.logix.service.CustomerServiceImpl.getCustomer(..))",
            returning = "obj")
    public void doAfterReturning(Object obj){
        log.info("@AfterReturning ------> " +
                "\n\t Customer: " + obj.toString() +
                "\n\t --> " + new Date());
    }
}
