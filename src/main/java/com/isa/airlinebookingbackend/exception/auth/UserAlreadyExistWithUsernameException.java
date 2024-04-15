package com.isa.airlinebookingbackend.exception.auth;

public class UserAlreadyExistWithUsernameException extends RuntimeException {
    public UserAlreadyExistWithUsernameException(String message) {
        super(message);
    }
}

