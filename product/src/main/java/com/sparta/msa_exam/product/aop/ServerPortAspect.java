package com.sparta.msa_exam.product.aop;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServerPortAspect {

    @Value("${server.port}")
    private String serverPort;

    @AfterReturning(value = "execution(* com.sparta.msa_exam.product.controller.ProductController.*(..))", returning = "response")
    public void addServerPortHeader(HttpServletResponse response) {
        log.info("ServerPortAspect!!");
        response.setHeader("Server_Port", serverPort);
    }
}
