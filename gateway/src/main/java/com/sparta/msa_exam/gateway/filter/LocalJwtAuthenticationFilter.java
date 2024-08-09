package com.sparta.msa_exam.gateway.filter;

import com.sparta.msa_exam.gateway.jwt.JwtUtil;
import com.sparta.msa_exam.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LocalJwtAuthenticationFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public LocalJwtAuthenticationFilter(JwtUtil jwtUtil, @Lazy AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (path.equals("/auth/signIn") || path.equals("/auth/signUp")) {
            return chain.filter(exchange);  // /signIn 경로는 필터를 적용하지 않음
        }

        String token = jwtUtil.getJwtFromHeader(exchange.getRequest());
        String username = authService.verify(jwtUtil.getUsername(token));

        if (token == null || !jwtUtil.validateToken(token) || username == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            log.info("UNAUTHORIZED");
            return exchange.getResponse().setComplete();
        }

        exchange.getRequest().mutate().header("username", username).build();
        log.info("token {}",token);

        return chain.filter(exchange);

    }
}
