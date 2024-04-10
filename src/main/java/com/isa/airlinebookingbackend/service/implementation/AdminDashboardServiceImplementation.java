package com.isa.airlinebookingbackend.service.implementation;

import com.isa.airlinebookingbackend.entity.Fleet;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.entity.Location;
import com.isa.airlinebookingbackend.payload.FleetPayload;
import com.isa.airlinebookingbackend.payload.FlightPayload;
import com.isa.airlinebookingbackend.payload.LocationPayload;
import com.isa.airlinebookingbackend.repository.FleetRepository;
import com.isa.airlinebookingbackend.repository.FlightRepository;
import com.isa.airlinebookingbackend.repository.LocationRepository;
import com.isa.airlinebookingbackend.service.AdminDashboardService;
import com.isa.airlinebookingbackend.utility.DomainPayloadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImplementation implements AdminDashboardService {
    private final LocationRepository locationRepository;
    private final FleetRepository fleetRepository;
    private final FlightRepository flightRepository;
    private final DomainPayloadMapper domainPayloadMapper;

    public ResponseEntity<HttpStatusCode> addFlightLocation(LocationPayload locationPayload) {
        locationRepository.findLocationByCode(locationPayload.code()).ifPresent(duplicatedLocation -> {
            throw new IllegalArgumentException();
        });

        Location location = domainPayloadMapper.locationPayloadToLocation(locationPayload);
        locationRepository.save(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatusCode> addFlight(FlightPayload flightPayload) {
        Fleet existingFlightFleet = fleetRepository.findById(flightPayload.fleetId()).orElseThrow();

        if (flightRepository.existsByFleetAndDepartureTimeOrArrivalTimeBetween(existingFlightFleet,
                flightPayload.departureTime(), flightPayload.arrivalTime())) {
            throw new IllegalArgumentException();
        }

        Flight flight = domainPayloadMapper.flightPayloadToFlight(flightPayload);

        Location existingDepartureLocation = locationRepository.findById(flight.getDepartureLocation().getId()).orElseThrow();
        Location existingArrivalLocation = locationRepository.findById(flight.getArrivalLocation().getId()).orElseThrow();

        flight.setDepartureLocation(existingDepartureLocation);
        flight.setArrivalLocation(existingArrivalLocation);
        flight.setFleet(existingFlightFleet);

        flight.setRemainingEconomySeats(existingFlightFleet.getTotalEconomySeats());
        flight.setRemainingPremiumSeats(existingFlightFleet.getTotalPremiumSeats());
        flight.setRemainingBusinessSeats(existingFlightFleet.getTotalBusinessSeats());

        flightRepository.save(flight);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<LocationPayload>> getFlightLocations() {
        List<Location> locations = locationRepository.findByOrderByIdAsc();
        return new ResponseEntity<>(domainPayloadMapper.locationsToLocationPayloads(locations), HttpStatus.OK);
    }

    public ResponseEntity<List<FleetPayload>> getFleets() {
        List<Fleet> fleets = fleetRepository.findAll();
        return new ResponseEntity<>(domainPayloadMapper.fleetsToFleetPayloads(fleets), HttpStatus.OK);
    }

    public ResponseEntity<List<FlightPayload>> getFlights() {
        List<Flight> flights = flightRepository.findByOrderByArrivalTimeDesc();

        List<FlightPayload> flightPayloads = domainPayloadMapper.flightsToFlightPayloads(flights);

        for (Flight flight : flights) {
            boolean hasBookings = isFlightHasBookings(flight);

            flightPayloads.stream().filter(flightPayload -> flightPayload.id().equals(flight.getId())).findFirst().ifPresent(flightPayload -> {
                FlightPayload updatedFlightPayload = new FlightPayload(
                        flightPayload.id(),
                        flightPayload.departureLocation(),
                        flightPayload.arrivalLocation(),
                        flightPayload.departureTime(),
                        flightPayload.arrivalTime(),
                        flightPayload.fleetId(),
                        flightPayload.economyFare(),
                        flightPayload.premiumFare(),
                        flightPayload.businessFare(),
                        flightPayload.remainingEconomySeats(),
                        flightPayload.remainingPremiumSeats(),
                        flightPayload.remainingBusinessSeats(),
                        flightPayload.flightCancelled(),
                        hasBookings
                );
                flightPayloads.remove(flightPayload);
                flightPayloads.add(updatedFlightPayload);
            });
        }

        return new ResponseEntity<>(flightPayloads, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatusCode> updateFlightLocation(LocationPayload locationPayload) {
        Location existingLocation = locationRepository.findById(locationPayload.id()).orElseThrow();

        String updatedLocationCode = locationPayload.code();
        if (!existingLocation.getCode().equals(updatedLocationCode)) {
            locationRepository.findLocationByCode(updatedLocationCode).ifPresent(duplicatedLocation -> {
                throw new IllegalArgumentException();
            });
            existingLocation.setCode(updatedLocationCode);
        }

        existingLocation.setCountry(locationPayload.country());
        existingLocation.setCityName(locationPayload.cityName());
        existingLocation.setAirportName(locationPayload.airportName());

        locationRepository.save(existingLocation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatusCode> updateFlight(FlightPayload flightPayload) {
        Flight existingFlight = flightRepository.findById(flightPayload.id()).orElseThrow();

        Fleet flightFleet;
        if (!flightPayload.fleetId().equals(existingFlight.getFleet().getId())) {
            if (isFlightHasBookings(existingFlight)) {
                throw new IllegalArgumentException();
            }

            flightFleet = fleetRepository.findById(flightPayload.fleetId()).orElseThrow();
            existingFlight.setFleet(flightFleet);

            existingFlight.setRemainingEconomySeats(flightFleet.getTotalEconomySeats());
            existingFlight.setRemainingPremiumSeats(flightFleet.getTotalPremiumSeats());
            existingFlight.setRemainingBusinessSeats(flightFleet.getTotalBusinessSeats());

            existingFlight.setEconomySeatFare(flightFleet.getTotalEconomySeats() != 0 ? flightPayload.economyFare() : null);
            existingFlight.setPremiumSeatFare(flightFleet.getTotalPremiumSeats() != 0 ? flightPayload.premiumFare() : null);
            existingFlight.setBusinessSeatFare(flightFleet.getTotalBusinessSeats() != 0 ? flightPayload.businessFare() : null);
        } else {
            flightFleet = existingFlight.getFleet();

            if (!isFlightHasBookings(existingFlight)) {
                existingFlight.setEconomySeatFare(flightFleet.getTotalEconomySeats() != 0 ? flightPayload.economyFare() : null);
                existingFlight.setPremiumSeatFare(flightFleet.getTotalPremiumSeats() != 0 ? flightPayload.premiumFare() : null);
                existingFlight.setBusinessSeatFare(flightFleet.getTotalBusinessSeats() != 0 ? flightPayload.businessFare() : null);
            }
        }

        if ((!flightPayload.departureTime().equals(existingFlight.getDepartureTime())
                && !flightPayload.arrivalTime().equals(existingFlight.getArrivalTime())) &&
                (flightRepository.existsByFleetAndDepartureTimeOrArrivalTimeBetween(flightFleet,
                        flightPayload.departureTime(), flightPayload.arrivalTime()))) {
            throw new IllegalArgumentException();
        }

        existingFlight.setDepartureTime(flightPayload.departureTime());
        existingFlight.setArrivalTime(flightPayload.arrivalTime());

        if (!flightPayload.departureLocation().equals(existingFlight.getDepartureLocation().getId())) {
            Location departureLocation = locationRepository.findById(flightPayload.departureLocation()).orElseThrow();
            existingFlight.setDepartureLocation(departureLocation);
        }

        if (!flightPayload.arrivalLocation().equals(existingFlight.getArrivalLocation().getId())) {
            Location arrivalLocation = locationRepository.findById(flightPayload.arrivalLocation()).orElseThrow();
            existingFlight.setArrivalLocation(arrivalLocation);
        }

        flightRepository.save(existingFlight);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatusCode> deleteFlightLocation(Long locationId) {
        Location existingLocation = locationRepository.findById(locationId).orElseThrow();
        if (flightRepository.existsByDepartureLocationOrArrivalLocation(existingLocation, existingLocation)) {
            throw new IllegalArgumentException();
        }

        existingLocation.setDeleted(true);

        locationRepository.save(existingLocation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatusCode> cancelFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        if (isFlightHasBookings(flight)) {
            throw new IllegalArgumentException();
        }

        flight.setFlightCancelled(true);

        flightRepository.save(flight);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isFlightHasBookings(Flight flight) {
        Fleet fleet = flight.getFleet();

        return !flight.getRemainingEconomySeats().equals(fleet.getTotalEconomySeats()) ||
                !flight.getRemainingPremiumSeats().equals(fleet.getTotalPremiumSeats()) ||
                !flight.getRemainingBusinessSeats().equals(fleet.getTotalBusinessSeats());
    }
}
