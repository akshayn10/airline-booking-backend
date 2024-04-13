package com.isa.airlinebookingbackend.seatbooking.booking.model;

import com.isa.airlinebookingbackend.seatbooking.passenger.model.Passenger;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long bookingId;
    @CreationTimestamp
    private Instant bookingDate;
    private int totalCost;
    private LocalDate travelDate;

    @OneToMany(mappedBy = "booking" , fetch = FetchType.EAGER)
    private List<Passenger> passengers;
}
