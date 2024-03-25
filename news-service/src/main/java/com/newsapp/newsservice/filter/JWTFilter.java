package com.newsapp.newsservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@Slf4j
public class JWTFilter extends GenericFilterBean {

    private final static String SECRET = "MyOwnSecret";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer"))
        {
            throw new ServletException("Missing or Invalid Authentication Header");
        }

        String jwtToken = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();
        httpRequest.setAttribute("userName", claims);
        filterChain.doFilter(request, response);
    }
}
