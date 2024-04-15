package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
