package com.isa.airlinebookingbackend.exception.booking;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}

