package com.isa.airlinebookingbackend.seatbooking.booking.repo;

import com.isa.airlinebookingbackend.seatbooking.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
