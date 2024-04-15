package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.payload.FleetPayload;
import com.isa.airlinebookingbackend.payload.FlightPayload;
import com.isa.airlinebookingbackend.payload.LocationPayload;
import com.isa.airlinebookingbackend.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/admin")
@RequiredArgsConstructor
public class AdminDashboardController {
    private final AdminDashboardService adminDashboardService;

    @PostMapping(path = "/location")
    public ResponseEntity<HttpStatusCode> addFlightLocation(@RequestBody LocationPayload locationPayload) {
        return adminDashboardService.addFlightLocation(locationPayload);
    }

    @PostMapping(path = "/flight")
    public ResponseEntity<HttpStatusCode> addFlight(@RequestBody FlightPayload flightPayload) {
        return adminDashboardService.addFlight(flightPayload);
    }

    @GetMapping(path = "/location")
    public ResponseEntity<List<LocationPayload>> getLocations() {
        return adminDashboardService.getFlightLocations();
    }

    @GetMapping(path = "/fleet")
    public ResponseEntity<List<FleetPayload>> getFleets() {
        return adminDashboardService.getFleets();
    }

    @GetMapping(path = "/flight")
    public ResponseEntity<List<FlightPayload>> getFlights() {
        return adminDashboardService.getFlights();
    }

    @PatchMapping(path = "/location")
    public ResponseEntity<HttpStatusCode> updateFlightLocation(@RequestBody LocationPayload locationPayload) {
        return adminDashboardService.updateFlightLocation(locationPayload);
    }

    @PatchMapping(path = "/flight")
    public ResponseEntity<HttpStatusCode> updateFlight(@RequestBody FlightPayload flightPayload) {
        return adminDashboardService.updateFlight(flightPayload);
    }

    @DeleteMapping(path = "/location/{id}")
    public ResponseEntity<HttpStatusCode> deleteFlightLocation(@PathVariable("id") Long id) {
        return adminDashboardService.deleteFlightLocation(id);
    }
}
