package com.scofen.study.springboot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by  GF  in  14:23 2017/9/22
 * Description:
 * Modified  By:
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {
    @Pointcut("execution(public * com.scofen.study.springboot.controller.*(..))")
    public void log(){

    }

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("url={}",request.getRequestURI());
        log.info("method={}",request.getMethod());
        log.info("Ip={}",request.getRemoteAddr());
        log.info("class_method={}", joinPoint.getSignature().
                getDeclaringTypeName() +"."
                + joinPoint.getSignature().getName());
        log.info("args={}",joinPoint.getArgs());

    }
    @After("log()")
    public void after(){
        log.info("切面方法之后");

    }

    @AfterReturning(pointcut = "log()",returning = "object")
    public void doAfterReturning(Object object){
        log.info("response={}",object.toString());
    }



}
