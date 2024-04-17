package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PassengerRepo extends JpaRepository<Passenger, Long> {
}
