package com.isa.airlinebookingbackend.seatbooking.passenger.service;

import com.isa.airlinebookingbackend.seatbooking.booking.model.Booking;
import com.isa.airlinebookingbackend.seatbooking.passenger.model.Passenger;
import com.isa.airlinebookingbackend.seatbooking.passenger.repo.PassengerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //facilitates the logging of messages
public class PassengerService {
    @Autowired
    private final PassengerRepo passengerRepo;

    public List<Passenger> getAllPassengers() {
       return passengerRepo.findAll();
    }

    public Passenger getPassengerById(long id) {
        Optional<Passenger> optionalPassenger = passengerRepo.findById(id);
        if (optionalPassenger.isPresent()) {
            return optionalPassenger.get();
        }
        log.info("Passenger with id {} not found", id);
        return null;
    }

    public List<Passenger> savePassengers(List<Passenger> passenger) {
//        for (Passenger p : passenger) {
//            System.out.println(p.getGender()+" "+p.getFirstName() + " " + p.getLastName());
//        }

        return passengerRepo.saveAll(passenger);
    }

    //add passengers via Booking
    public String addPassengers(List<Passenger> passengers, Booking booking) {
        for(Passenger passenger : passengers) {
            passenger.setBooking(booking);
            passengerRepo.save(passenger);
        }

        return "passengerRepo.saveAll(passenger)";
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
        return passengerRepo.findAllByBookingBookingId(bookingId);
    }
}