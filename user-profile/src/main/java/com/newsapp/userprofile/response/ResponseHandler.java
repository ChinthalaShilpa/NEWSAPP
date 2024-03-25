package com.newsapp.userprofile.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    //return response entity with a hashmap containing message, status value and responseObject
    public static Object generateResponse(String message) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",message);
        return map;
    }
}
