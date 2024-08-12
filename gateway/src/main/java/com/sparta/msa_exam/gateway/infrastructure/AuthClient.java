package com.sparta.msa_exam.gateway.infrastructure;

import com.sparta.msa_exam.gateway.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service", configuration = FeginConfig.class)
public interface AuthClient {

    @PostMapping("/auth/verification")
    String verify(@RequestParam String username);
}
