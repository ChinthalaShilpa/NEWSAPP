package com.newsapp.authenticationserver.exception;

import com.newsapp.authenticationserver.response.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomResponse<String>> inCaseOfUserNotFound(UserNotFoundException e) {
        log.info("User Not found exception encountered");
        CustomResponse<String> customResponse = new CustomResponse<>();
        customResponse.setResponse(e.getMessage());
        return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<CustomResponse<String>> inCaseInvalidTokenException(InvalidTokenException e) {
        log.info("Token Missing or Invaid token provided....");
        CustomResponse<String> customResponse = new CustomResponse<>();
        customResponse.setResponse(e.getMessage());
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }
}
