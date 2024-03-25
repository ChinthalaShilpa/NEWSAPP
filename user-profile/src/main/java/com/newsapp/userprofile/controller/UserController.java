package com.newsapp.userprofile.controller;

import com.newsapp.userprofile.exception.UserNameIsTaken;
import com.newsapp.userprofile.model.User;
import com.newsapp.userprofile.response.ResponseHandler;
import com.newsapp.userprofile.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(
        path = "/api/v1.0/user"
)
@OpenAPIDefinition(
        info = @Info(
                title = "News App user profile service API",
                description = "This API provides endpoints for users to register."
        )
)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    @PostMapping(
            path = "/register"
    )
    public ResponseEntity<Object> registerUser(@RequestBody User user){
            User userCreated = userService.registerUser(user);
            kafkaTemplate.send(topic.name(),"user created " + userCreated);
            return new ResponseEntity<>(
                    ResponseHandler.generateResponse("User created successfully"),
                    HttpStatus.CREATED
            );
    }
}
