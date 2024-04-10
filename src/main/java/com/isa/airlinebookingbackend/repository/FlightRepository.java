package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.Fleet;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    boolean existsByDepartureLocationOrArrivalLocation(Location departureLocation, Location arrivalLocation);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM Flight f WHERE f.fleet = :fleet " +
            "AND ((f.departureTime BETWEEN :startTime AND :endTime) OR (f.arrivalTime BETWEEN :startTime AND :endTime))"
    )
    boolean existsByFleetAndDepartureTimeOrArrivalTimeBetween(@Param("fleet") Fleet fleet,
                                                              @Param("startTime") OffsetDateTime departureTime,
                                                              @Param("endTime") OffsetDateTime arrivalTime);
}
