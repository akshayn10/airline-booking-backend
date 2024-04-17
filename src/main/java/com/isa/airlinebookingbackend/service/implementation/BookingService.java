package com.isa.airlinebookingbackend.service.implementation;

import com.isa.airlinebookingbackend.dto.booking.request.BookingRequestDTO;
import com.isa.airlinebookingbackend.dto.booking.request.PassengerRequestDTO;
import com.isa.airlinebookingbackend.entity.Booking;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.entity.Passenger;
import com.isa.airlinebookingbackend.entity.auth.User;
import com.isa.airlinebookingbackend.exception.auth.UserNotFoundException;
import com.isa.airlinebookingbackend.exception.booking.BookingNotFoundException;
import com.isa.airlinebookingbackend.exception.booking.FlightNotFoundException;
import com.isa.airlinebookingbackend.repository.BookingRepo;
import com.isa.airlinebookingbackend.repository.FlightRepository;
import com.isa.airlinebookingbackend.repository.PassengerRepo;
import com.isa.airlinebookingbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;


    public Booking addBooking(BookingRequestDTO requestDTO) {

        List<Passenger> passengers = new ArrayList<>();
        for (PassengerRequestDTO p : requestDTO.getPassengers()) {

            Passenger passenger = new Passenger();
            passenger.setFirstName(p.getFirstName());
            passenger.setLastName(p.getLastName());
            passenger.setPassportNo(p.getPassportNo());
            passenger.setMealPreference(p.getMealPreference());
            passenger.setDateOfBirth(p.getDateOfBirth());
            passenger.setGender(p.getGender());

            passengers.add(passenger);
        }
        passengerRepo.saveAll(passengers);
        User user = userRepository.findByEmail(requestDTO.getUserEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Flight flight = flightRepository.findById(requestDTO.getFlightId()).orElseThrow(() -> new FlightNotFoundException("Flight Not Found"));
        Booking booking = Booking.builder()
                .seatTypeBooked(requestDTO.getSeatTypeBooked())
                .noOfSeatBooked(requestDTO.getNoOfSeatBooked())
                .flight(flight)
                .totalCost(calculateTotalCost(requestDTO.getSeatTypeBooked(), requestDTO.getNoOfSeatBooked(), flight))
                .user(user)
                .seatNumbers(requestDTO.getSeatNumbers())
                .passengers(passengers).build();
        Booking savedBooking = bookingRepo.save(booking);
        return savedBooking;
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found with id " + bookingId));
        booking.setCancelled(true);
        booking.setSeatNumbers(new int[0]);
        bookingRepo.save(booking);
        return booking;
    }

    public int[] getBookedSeats(Long flightId) {
        List<Integer> seatsNumberList = new ArrayList<>();
        var flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight not found with id " + flightId));
        var bookingsForFlight = bookingRepo.findAllByFlight(flight);
        for (Booking booking : bookingsForFlight) {
            int[] seatNumbersArray = booking.getSeatNumbers();
            if(seatNumbersArray != null) {
                for (int seatNumber : seatNumbersArray) {
                    seatsNumberList.add(seatNumber);
                }
            }
        }
        return seatsNumberList.stream().mapToInt(Integer::intValue).toArray();


    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public float calculateTotalCost(String seatType, int noOfSeatBooked, Flight flight) {
        float totalCost = 0.0f;
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
        return totalCost;
    }
}
