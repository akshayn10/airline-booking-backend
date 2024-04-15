package com.isa.airlinebookingbackend.exception.auth;

public class OTPExpiredException extends RuntimeException {
    public OTPExpiredException(String message) {
        super(message);
    }
}
