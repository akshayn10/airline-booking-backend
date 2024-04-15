package com.isa.airlinebookingbackend.payload;

public record LocationPayload(Long id, String cityName, String code, String country, String airportName) {
}
