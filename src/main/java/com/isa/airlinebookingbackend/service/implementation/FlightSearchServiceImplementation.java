package com.isa.airlinebookingbackend.service.implementation;

import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.entity.Location;
import com.isa.airlinebookingbackend.payload.FlightPayload;
import com.isa.airlinebookingbackend.payload.LocationPayload;
import com.isa.airlinebookingbackend.repository.LocationRepository;
import com.isa.airlinebookingbackend.repository.FlightRepository;
import com.isa.airlinebookingbackend.service.FlightSearchService;
import com.isa.airlinebookingbackend.utility.DomainPayloadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImplementation implements FlightSearchService{
    private final LocationRepository locationRepository;
    private final DomainPayloadMapper domainPayloadMapper;
    private final FlightRepository flightRepository;

    public ResponseEntity<List<LocationPayload>> getFlightLocations() {
        List<Location> locations = locationRepository.findAll();
        return new ResponseEntity<>(domainPayloadMapper.locationsToLocationPayloads(locations), HttpStatus.OK);
    }

    public ResponseEntity<List<FlightPayload>> getFlights() {
        List<Flight> flights = flightRepository.findAllByFlightCancelledFalse();
        return new ResponseEntity<>(domainPayloadMapper.flightsToFlightPayloads(flights), HttpStatus.OK);
    }

}
