package com.sparta.msa_exam.product.aop;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class ServerPortAspect {

    @Value("${server.port}")
    private String serverPort;

    @Pointcut("execution(* com.sparta.msa_exam.product.controller.ProductController.*(..))")
    private void controller() {

    }

    @Around("controller()")
    public Object addServerPortHeader(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        try {
            log.info("ServerPortAspect");
            return joinPoint.proceed();
        } finally {
            response.setHeader("Server_Port", serverPort);
        }

    }
}
