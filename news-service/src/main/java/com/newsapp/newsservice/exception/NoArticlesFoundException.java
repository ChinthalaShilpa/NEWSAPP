package com.newsapp.newsservice.exception;

public class NoArticlesFoundException extends Throwable {
    public NoArticlesFoundException(String noArticlesFound) {
        super(noArticlesFound);
    }
}
