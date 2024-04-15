package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findLocationByCode(String code);

    List<Location> findByOrderByCodeAsc();
}
