package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Booking;
import com.isa.airlinebookingbackend.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUser(User user);
}
