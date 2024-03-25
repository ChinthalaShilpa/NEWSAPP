package com.newsapp.wishlistservice.filter;

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
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        log.info("token received -> ",authHeader.toString());

        if(authHeader == null || !authHeader.startsWith("Bearer"))
        {
            throw new ServletException("Missing or Invalid Authentication Header");
        }

        String jwtToken = authHeader.substring(7);

        Claims claims = Jwts.parser().setSigningKey("MyOwnSecret").parseClaimsJws(jwtToken).getBody();
        httpRequest.setAttribute("userName", claims);
        filterChain.doFilter(request, response);
    }
}
