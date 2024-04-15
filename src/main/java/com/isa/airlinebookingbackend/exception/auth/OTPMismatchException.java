package com.isa.airlinebookingbackend.exception.auth;

public class OTPMismatchException extends RuntimeException {
    public OTPMismatchException(String message) {
        super(message);
    }
}
