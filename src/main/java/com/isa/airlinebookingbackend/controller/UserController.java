package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.payload.LocationPayload;
import com.isa.airlinebookingbackend.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/search")
@RequiredArgsConstructor
public class UserController {
    private final FlightSearchService adminDashboardService;


    @GetMapping(path = "/location")
    public ResponseEntity<List<LocationPayload>> getLocations() {
        return adminDashboardService.getFlightLocations();
    }

    
}

