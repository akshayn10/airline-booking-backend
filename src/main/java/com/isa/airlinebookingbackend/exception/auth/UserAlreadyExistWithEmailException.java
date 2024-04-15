package com.isa.airlinebookingbackend.exception.auth;

public class UserAlreadyExistWithEmailException extends RuntimeException {
    public UserAlreadyExistWithEmailException(String message) {
        super(message);
    }
}

