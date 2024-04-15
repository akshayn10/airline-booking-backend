package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Long> {
}