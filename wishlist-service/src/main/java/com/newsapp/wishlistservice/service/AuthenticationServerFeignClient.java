package com.newsapp.wishlistservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="NEWSAPP-AUTH-SERVICE", url="http://localhost:8080/api/v1.0/auth")
public interface AuthenticationServerFeignClient {
    @GetMapping("/getusername")
    String getUserName(@RequestHeader(name = "Authorization") String token);
}
