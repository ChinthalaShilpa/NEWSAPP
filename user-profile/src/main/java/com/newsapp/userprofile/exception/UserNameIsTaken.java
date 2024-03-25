package com.newsapp.userprofile.exception;

public class UserNameIsTaken extends RuntimeException {
    public UserNameIsTaken(String s) {
        super(s);
    }
}
