package com.isa.airlinebookingbackend.service;

import com.isa.airlinebookingbackend.payload.LocationPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlightSearchService {

    ResponseEntity<List<LocationPayload>> getFlightLocations();

}


