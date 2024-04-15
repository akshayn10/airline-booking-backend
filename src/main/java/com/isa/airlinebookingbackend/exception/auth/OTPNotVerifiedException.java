package com.isa.airlinebookingbackend.exception.auth;

public class OTPNotVerifiedException extends RuntimeException {
    public OTPNotVerifiedException(String message) {
        super(message);
    }
}
