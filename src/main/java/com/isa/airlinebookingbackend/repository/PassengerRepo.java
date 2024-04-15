package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
    List<Passenger> findAllByBookingBookingId(long bookingId);
}
