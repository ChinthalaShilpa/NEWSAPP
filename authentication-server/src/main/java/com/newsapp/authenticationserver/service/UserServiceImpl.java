package com.newsapp.authenticationserver.service;

import com.newsapp.authenticationserver.exception.UserNotFoundException;
import com.newsapp.authenticationserver.model.User;
import com.newsapp.authenticationserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public boolean validateUserService(String userName, String password) {
        log.info("The call entered validateUserService() and should check for validation");
        User user = userRepository.validateUser(userName, password);
        if (user != null) {
            log.info("User found");
            return true;
        }
        else {
            log.info("User not found");
            return false;
        }
    }
}
