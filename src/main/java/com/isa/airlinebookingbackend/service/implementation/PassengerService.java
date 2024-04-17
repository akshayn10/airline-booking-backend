package com.isa.airlinebookingbackend.service.implementation;

import com.isa.airlinebookingbackend.entity.Booking;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.entity.Passenger;
import com.isa.airlinebookingbackend.repository.FlightRepository;
import com.isa.airlinebookingbackend.repository.PassengerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassengerService {
    @Autowired
    private final PassengerRepo passengerRepo;

    private final FlightRepository flightRepo;

//    public List<Passenger> getAllPassengers() {
//        List<Passenger> passengers = passengerRepo.findAll();
//        return passengers.stream()
//                .map(passenger -> {
//                    Passenger passengerDTO = new Passenger();
//                    passengerDTO.setId(passenger.getId());
//                    passengerDTO.setFirstName(passenger.getFirstName());
//                    passengerDTO.setLastName(passenger.getLastName());
//                    passengerDTO.setPassportNo(passenger.getPassportNo());
//                    passengerDTO.setMealPreference(passenger.getMealPreference());
//                    passengerDTO.setDateOfBirth(passenger.getDateOfBirth());
//                    passengerDTO.setGender(passenger.getGender());
//
//                    // Map booking information
//                    Booking booking = passenger.getBooking();
//                    if (booking != null) {
//                        Booking bookingDTO = new Booking();
//                        bookingDTO.setBookingId(booking.getBookingId());
//                        bookingDTO.setBookingDate(booking.getBookingDate());
//                        bookingDTO.setTotalCost(booking.getTotalCost());
//                        bookingDTO.setTravelDate(booking.getTravelDate());
//                        bookingDTO.setNoOfSeatBooked(booking.getNoOfSeatBooked());
//                        bookingDTO.setSeatTypeBooked(booking.getSeatTypeBooked());
//                        bookingDTO.setFlightId(booking.getFlightId());
//                        passengerDTO.setBooking(bookingDTO);
//                    }
//
//                    return passengerDTO;
//                })
//                .collect(Collectors.toList());
//    }

    public Passenger getPassengerById(long id) {
        Optional<Passenger> optionalPassenger = passengerRepo.findById(id);
        if (optionalPassenger.isPresent()) {
            return optionalPassenger.get();
        }
        log.info("Passenger with id {} not found", id);
        return null;
    }

    public List<Passenger> savePassengers(List<Passenger> passenger) {
        return passengerRepo.saveAll(passenger);
    }

    public Passenger updatePassenger(Passenger passenger){
        Optional<Passenger> existingPassenger = passengerRepo.findById(passenger.getId());
        if (existingPassenger.isPresent()) {
            Passenger updatedPassenger = passengerRepo.save(passenger);
            return updatedPassenger;
        }
        return null;
    }

    public void deletePassenger(long id) {
        passengerRepo.deleteById(id);
    }

    public List<Passenger> getAllPassengersByBookingId(long bookingId) {
//        return passengerRepo.findAllByBookingBookingId(bookingId);
        return null;
    }

    public Flight getFlightbyId(long id) {
        Optional<Flight> optionalFlight = flightRepo.findById(id);
        System.out.println("Flight Id :"+optionalFlight.get().getId());
        System.out.println("Flight fare :"+optionalFlight.get().getEconomySeatFare());
        System.out.println("Flight fare :"+optionalFlight.get().getArrivalTime());

            return optionalFlight.get();
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }
}