package com.isa.airlinebookingbackend.service.implementation;

import com.isa.airlinebookingbackend.entity.Booking;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.repository.BookingRepo;
import com.isa.airlinebookingbackend.repository.FlightRepository;
import com.isa.airlinebookingbackend.repository.PassengerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlightRepository flightRepository;

    private float totalCost;



    public Booking addBooking(Booking booking) {
            Optional<Flight> existingFlight;
            Flight flight;
            existingFlight = flightRepository.findById(booking.getFlightId());
            flight = existingFlight.get();

            calculateTotalCost(booking.getSeatTypeBooked(),booking.getNoOfSeatBooked(),flight);
            booking.setTotalCost(totalCost);
            Booking savedBooking = bookingRepo.save(booking);

            passengerService.addPassengers(booking.getPassengers(),savedBooking);
            return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public void calculateTotalCost(String seatType, int noOfSeatBooked, Flight flight ) {
        switch (seatType) {
                case "economy":
                    totalCost = noOfSeatBooked * flight.getEconomySeatFare();
                    break;
                case "business":
                    totalCost = noOfSeatBooked * flight.getBusinessSeatFare();
                    break;
                case "premium":
                    totalCost = noOfSeatBooked * flight.getPremiumSeatFare();
                    break;
                default:
                throw new IllegalArgumentException("Invalid seat type: " + seatType);
        }
    }
}
