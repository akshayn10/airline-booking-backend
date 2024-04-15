package com.isa.airlinebookingbackend.entity;

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

    private float totalCost;

    private LocalDate travelDate;

    private int noOfSeatBooked;

    private String seatTypeBooked;

    private long flightId;

    @OneToMany(mappedBy = "booking" , fetch = FetchType.EAGER)

    private List<Passenger> passengers;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingDate=" + bookingDate +
                ", totalCost=" + totalCost +
                ", travelDate=" + travelDate +
                ", noOfSeatBooked=" + noOfSeatBooked +
                ", seatTypeBooked='" + seatTypeBooked + '\'' +
                ", flightId=" + flightId +
                '}';
    }
}
