package com.newsapp.wishlistservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ArticleAlreadyWishlisted.class)
    public ResponseEntity<String> handleCustomValidationException(ArticleAlreadyWishlisted ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoArticlesWishlisted.class)
    public ResponseEntity<String> inCaseArticlesNotWishListed(NoArticlesWishlisted ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}