package com.isa.airlinebookingbackend.exception.auth;

public class OTPNotFoundForUserException extends RuntimeException {
    public OTPNotFoundForUserException(String message) {
        super(message);
    }
}
