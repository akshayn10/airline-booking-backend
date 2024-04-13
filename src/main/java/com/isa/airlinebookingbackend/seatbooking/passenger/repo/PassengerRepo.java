package com.isa.airlinebookingbackend.seatbooking.passenger.repo;

import com.isa.airlinebookingbackend.seatbooking.passenger.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
    List<Passenger> findAllByBookingBookingId(long bookingId);
}
