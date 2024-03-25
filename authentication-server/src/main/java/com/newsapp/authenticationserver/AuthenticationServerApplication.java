package com.newsapp.authenticationserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class AuthenticationServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
		log.info("The AuthenticationServerApplication has started");
	}

}
