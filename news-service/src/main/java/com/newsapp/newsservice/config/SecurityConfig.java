package com.newsapp.newsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(Arrays.asList("*"));
                    cors.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    return cors;//allowing cross origin requests
                })
                .and()
                .csrf().disable()
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .disable();
        return http.build();
    }
}
