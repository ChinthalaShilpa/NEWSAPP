package com.newsapp.authenticationserver.exception;

public class InvalidTokenException extends Throwable {
    public InvalidTokenException(String missingOrInvalidAuthenticationHeader) {
        super(missingOrInvalidAuthenticationHeader);
    }
}
