package com.sparta.msa_exam.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {


    @Value("${service.jwt.secret-key}")
    private String secretKey;

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";



    public String getJwtFromHeader(HttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token, ServerWebExchange exchange) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token).getPayload();
            if (claims.getExpiration().before(new Date())) {
                log.info("expiration");
                return false;
            }
            log.info("#####payload :: " + claims);
            exchange.getRequest().mutate()
                    .header("X-User-Id", claims.get("user_id").toString())
                    .build();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
