package com.isa.airlinebookingbackend.exception.auth;

public class PasswordMisMatchException extends RuntimeException {
    public PasswordMisMatchException(String message) {
        super(message);
    }
}
