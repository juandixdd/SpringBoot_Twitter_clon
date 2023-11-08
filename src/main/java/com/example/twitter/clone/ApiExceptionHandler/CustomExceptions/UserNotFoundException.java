package com.example.twitter.clone.ApiExceptionHandler.CustomExceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
