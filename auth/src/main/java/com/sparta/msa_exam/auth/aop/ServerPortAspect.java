package com.sparta.msa_exam.auth.aop;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ServerPortAspect {

    @Value("${server.port}")
    private String serverPort;

    @AfterReturning(pointcut = "execution(* com.sparta.msa_exam.auth.controller.AuthController.*(..))")
    public void addServerPortHeader() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        log.info("ServerPortAspect!!");
        response.setHeader("Server_Port", serverPort);
    }
}
