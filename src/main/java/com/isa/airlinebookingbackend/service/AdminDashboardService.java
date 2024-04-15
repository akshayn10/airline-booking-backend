package com.isa.airlinebookingbackend.service;

import com.isa.airlinebookingbackend.payload.FleetPayload;
import com.isa.airlinebookingbackend.payload.FlightPayload;
import com.isa.airlinebookingbackend.payload.LocationPayload;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminDashboardService {
    ResponseEntity<HttpStatusCode> addFlightLocation(LocationPayload locationPayload);

    ResponseEntity<HttpStatusCode> addFlight(FlightPayload flightPayload);

    ResponseEntity<List<LocationPayload>> getFlightLocations();

    ResponseEntity<List<FleetPayload>> getFleets();

    ResponseEntity<List<FlightPayload>> getFlights();

    ResponseEntity<HttpStatusCode> updateFlightLocation(LocationPayload locationPayload);

    ResponseEntity<HttpStatusCode> updateFlight(FlightPayload flightPayload);

    ResponseEntity<HttpStatusCode> deleteFlightLocation(Long locationId);
}
