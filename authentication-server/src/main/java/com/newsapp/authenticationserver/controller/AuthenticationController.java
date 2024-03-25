package com.newsapp.authenticationserver.controller;

import com.newsapp.authenticationserver.exception.InvalidTokenException;
import com.newsapp.authenticationserver.exception.UserNotFoundException;
import com.newsapp.authenticationserver.request.LoginRequest;
import com.newsapp.authenticationserver.response.CustomResponse;
import com.newsapp.authenticationserver.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
    path = "/api/v1.0/auth"
)
@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "News App Authentication server API",
                description = "This API provides endpoints for authenticating users."
        )
)
public class AuthenticationController {

    @Value("${newsapp.secret}")
    private String secret;

    private Map<String,String> map = new HashMap<>();
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        log.info("Client made login request with creds : ", loginRequest.toString());
        try {
            String jwtToken = generateToken(loginRequest.getUserName(), loginRequest.getPassword());
            map.put("message", "User Successfully LoggedIn");
            map.put("token", jwtToken);
        }catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            Map<String,String> map1 = new HashMap<>();
            map1.put("message", e.getMessage());
            return new ResponseEntity<>(map1, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new CustomResponse<>(map),HttpStatus.OK);
    }

    @GetMapping("/getusername")
    public ResponseEntity<String> getUserName(@RequestHeader(name = "Authorization") String token) throws InvalidTokenException {
        log.info("get user name request in controller of auth");
        return new ResponseEntity<>(getUserNameFromToken(token),HttpStatus.OK);
    }

    private String generateToken(String username, String password) throws ServletException, UserNotFoundException {
        log.info("Requested to generate the token");
        String jwtToken = "";
        if(username == null || password == null) {
            log.info("Resquested with null credentials");
            throw new ServletException("Please send valid username and password");
        }
        log.info("validating user creds against db");
        boolean flag = userService.validateUserService(username, password);
        if(!flag) {
            log.info("User with provided creds not found");
            throw new UserNotFoundException("User with provided creds not found");
        }
        else {
            log.info("User validated and generating JWT token");
            jwtToken = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3000000))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        }
        log.info("Token Generation successfull");
        return jwtToken;
    }

    private String getUserNameFromToken(String token) throws InvalidTokenException {
        log.info("trying to get username from token provided");
        if(token == null || !token.startsWith("Bearer"))
        {
            log.info("But token not provided.");
            throw new InvalidTokenException("Missing or Invalid Authentication Header");
        }
        try{
            String jwtToken = token.substring(7);
            Claims claims = Jwts.parser().setSigningKey("MyOwnSecret").parseClaimsJws(jwtToken).getBody();
            log.info("Username from token is extracted success");
            return claims.get("sub", String.class);
        }
        catch (Exception e){
            throw new InvalidTokenException("Invalid token");
        }
    }
}
