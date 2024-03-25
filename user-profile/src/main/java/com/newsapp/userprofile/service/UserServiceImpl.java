package com.newsapp.userprofile.service;

import com.newsapp.userprofile.exception.UserNameIsTaken;
import com.newsapp.userprofile.model.User;
import com.newsapp.userprofile.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public User registerUser(User user) {
        Optional<User> userFound = userRepo.findUserByUserName(user.getUserName());
        if(userFound.isEmpty()){
            return userRepo.save(user);
        }
        else{
            throw new UserNameIsTaken("Username is taken. Try using different username");
        }
    }
}
